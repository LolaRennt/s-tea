package org.sky.auto.story.listener;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;


public class ProxyStoryListener {
	private static List<StoryListener> runListeners = new ArrayList<StoryListener>();
	private static StoryListener dispatcher = (StoryListener)Proxy.newProxyInstance(StoryListener.class.getClassLoader(), 
			new Class<?>[]{StoryListener.class},
			new InvocationHandler() {				
				public Object invoke(Object proxy, Method method, Object[] arg2) throws Throwable {
					try{
						for(StoryListener runListener:runListeners){
							method.invoke(runListener, arg2);
						}
					}catch(InvocationTargetException e){
						throw e.getTargetException();
					}
					return null;
				}
			});
	
	
	public static void register(StoryListener runListener){
		runListeners.add(runListener);
	}
	
	public static void unregister(StoryListener runListener){
		runListeners.remove(runListener);
	}

	public static StoryListener getDispatcher() {
		return dispatcher;
	}
}
