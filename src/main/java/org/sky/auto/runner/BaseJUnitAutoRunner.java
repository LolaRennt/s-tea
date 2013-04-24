package org.sky.auto.runner;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.databene.feed4junit.Feeder;
import org.junit.runner.notification.RunListener;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.RunnerScheduler;
import org.junit.runners.model.Statement;
import org.sky.auto.anno.InterceptorClass;
import org.sky.auto.anno.JUnitRunListener;
import org.sky.auto.anno.RunListenerRegister;
import org.sky.auto.anno.ThreadRunner;
import org.sky.auto.base.MyFile;
import org.sky.auto.intrumentation.ClassPool;
import org.sky.auto.proxy.ProxyJUnitRunListener;
import org.sky.auto.report.ReportListener;
import org.sky.auto.runner.statement.Interceptor;
import org.sky.auto.runner.statement.InterceptorStatement;

public class BaseJUnitAutoRunner extends Feeder{
	private Logger logger = Logger.getLogger(BaseJUnitAutoRunner.class);
	public BaseJUnitAutoRunner(final Class<?> klass)
			throws InitializationError {
		super(klass);
		PropertyConfigurator.configure("resource"+File.separator+"log4j.properties");
		setScheduler(new RunnerScheduler() {
			ExecutorService executorService = Executors.newFixedThreadPool(
                    klass.isAnnotationPresent(ThreadRunner.class) ?
                            klass.getAnnotation(ThreadRunner.class).threads() :
                            (int) (Runtime.getRuntime().availableProcessors() * 1.5),
                    new NamedThreadFactory(klass.getSimpleName()));
            CompletionService<Void> completionService = new ExecutorCompletionService<Void>(executorService);
            Queue<Future<Void>> tasks = new LinkedList<Future<Void>>();
			
			public void schedule(Runnable childStatement) {
				tasks.offer(completionService.submit(childStatement, null));
			}
			
			
			public void finished() {
				try {
                    while (!tasks.isEmpty())
                        tasks.remove(completionService.take());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    while (!tasks.isEmpty())
                        tasks.poll().cancel(true);
                    executorService.shutdownNow();
                }
			}
			
		});
	}
	static final class NamedThreadFactory implements ThreadFactory {
        static final AtomicInteger poolNumber = new AtomicInteger(1);
        final AtomicInteger threadNumber = new AtomicInteger(1);
        final ThreadGroup group;

        NamedThreadFactory(String poolName) {
            group = new ThreadGroup(poolName + "-" + poolNumber.getAndIncrement());
        }

        
        public Thread newThread(Runnable r) {
            return new Thread(group, r, group.getName() + "-thread-" + threadNumber.getAndIncrement(), 0);
        }
    }
	
	
	protected Statement methodInvoker(FrameworkMethod method, Object test) {
		InterceptorStatement statement = new InterceptorStatement(method, test);
		//statement.addInterceptor(new ThreadLocalResetInterceptor());
		if(test.getClass().isAnnotationPresent(InterceptorClass.class)){
			InterceptorClass anno = test.getClass().getAnnotation(InterceptorClass.class);
			Class<?>[] clazzs = anno.value();
			try{
				for(Class<?> clazz : clazzs){
					statement.addInterceptor((Interceptor)clazz.newInstance());
				}
			}catch(IllegalAccessException ilex){
				ilex.printStackTrace();
			}catch(InstantiationException e){
				e.printStackTrace();
			}
		}
		return statement;
	//	return new InvokeMethod(method, test);
	}
	
	
	
	public void run(RunNotifier rn) {
		//AutoBase.setLogStarted();
		if(new File("report").exists()){
			MyFile.delFolder("report");
			logger.info("初始化了报告文件夹");
		}
		if(new File("templet"+File.separator+"LOG.txt").exists()){
			new File("templet"+File.separator+"LOG.txt").delete();
		}
		rn.addListener(new ReportListener());
		MyFile.createFile("templet"+File.separator+"LOG.txt");
		if(getDescription().getAnnotation(JUnitRunListener.class)!=null){
			JUnitRunListener jrl = getDescription().getAnnotation(JUnitRunListener.class);
			Class<?>[] rls = jrl.value();
			for(Class<?> rl : rls){
				try {
					rn.addListener((RunListener) rl.newInstance());
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		Set<Class<?>> cls = ClassPool.getClassPool();
		for(Class<?>clazz : cls){
			if(clazz.isAnnotationPresent(RunListenerRegister.class)){
				try {
					ProxyJUnitRunListener.register((RunListener) clazz.newInstance());
					logger.info("扫描到了运行器级别的监听器"+clazz.getName());
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		if(ProxyJUnitRunListener.dispatcher().size()!=0){
			for(RunListener rl:ProxyJUnitRunListener.dispatcher()){
				rn.addListener(rl);
			}
		}
		
		super.run(rn);
	}
}
