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
	
	
	public static void register(RunnerListener runListener){
		runListeners.add(runListener);
	}
	
	public static void unregister(RunnerListener runListener){
		runListeners.remove(runListener);
	}

	public static RunnerListener getDispatcher() {
		return dispatcher;
	}
	
}
