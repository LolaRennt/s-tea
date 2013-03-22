package org.sky.auto.proxy;

import java.util.ArrayList;
import java.util.List;

import org.junit.runner.notification.RunListener;
import org.sky.auto.exception.NotFoundListenerException;

/**这个类是JUnit的自身监听器的代理实现，可以通过这个类来静态的注册*/

public class ProxyJUnitRunListener {
	static List<RunListener> eventsList = new ArrayList<RunListener>();
	
	public static void register(RunListener runlistener){
		if(!containsListenr(runlistener)){
			eventsList.add(runlistener);
		}
	}
	
	public static void unregister(RunListener runlistener){
		if(containsListenr(runlistener)){
			eventsList.remove(runlistener);
		}else{
			throw new NotFoundListenerException(runlistener.getClass().getName()+"->没有找到这个监听器！");
		}
		
	}
	
	public static List<RunListener> dispatcher(){
		return eventsList;
	}
	
	public static boolean containsListenr(RunListener runlistener){
		for(RunListener rl:eventsList){
			if(rl.getClass().getName().equals(runlistener.getClass().getName())){
				return true;
			}
		}
		return false;
	}
	
}
