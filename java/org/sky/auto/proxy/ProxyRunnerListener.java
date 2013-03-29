package org.sky.auto.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

import org.sky.auto.driver.event.RunnerListener;

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
	
	
	
	public static void unregister(Class<?>clazz){
		RunnerListener listener =null;
		for(RunnerListener rl : runListeners){
			//RunnerListener listener=null;
			if(rl.getClass().getName().equals(clazz.getName())){
				listener=rl;
			}
		}
		runListeners.remove(listener);
		
	}

	public static RunnerListener getDispatcher() {
		return dispatcher;
	}
	
	public static void register(Class<?>clazz){
		if(!containsListener(clazz)){
			try {
				runListeners.add((RunnerListener) clazz.newInstance());
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static boolean containsListener(Class<?>clazz){
		for(RunnerListener rl : runListeners){
			if(rl.getClass().getName().equals(clazz.getName())){
				return true;
			}
		}
		return false;
	}
	
}
