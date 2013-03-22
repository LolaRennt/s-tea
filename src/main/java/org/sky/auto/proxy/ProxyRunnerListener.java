package org.sky.auto.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

import org.sky.auto.driver.event.RunnerListener;
import org.sky.auto.exception.NotFoundListenerException;

public class ProxyRunnerListener {
	
	private static List<RunnerListener> runListeners = new ArrayList<RunnerListener>();
	private static RunnerListener dispatcher = (RunnerListener)Proxy.newProxyInstance(RunnerListener.class.getClassLoader(), 
			new Class<?>[]{RunnerListener.class},
			new InvocationHandler() {				
				public Object invoke(Object proxy, Method method, Object[] arg2) throws Throwable {
					try{
						for(RunnerListener runListener:runListeners){
							method.invoke(runListener, arg2);
						}
					}catch(InvocationTargetException e){
						throw e.getTargetException();
					}
					return null;
				}
			});
	
	
	
	public static void unregister(RunnerListener runListener){
		if(containsListener(runListener)){
			runListeners.remove(runListener);
		}else{
			throw new NotFoundListenerException(runListener.getClass().getName()+"->没有找到这个监听器");
		}
		
	}

	public static RunnerListener getDispatcher() {
		return dispatcher;
	}
	
	public static void register(RunnerListener runListener){
		if(!containsListener(runListener)){
			runListeners.add(runListener);
		}
		
	}
	
	public static boolean containsListener(RunnerListener runListener){
		for(RunnerListener rl : runListeners){
			if(rl.getClass().getName().equals(runListener.getClass().getName())){
				return true;
			}
		}
		return false;
	}
	
}
