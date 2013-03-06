package org.sky.auto.proxy;

import java.util.ArrayList;
import java.util.List;

import org.junit.runner.notification.RunListener;

/**这个类是JUnit的自身监听器的代理实现，可以通过这个类来静态的注册*/

public class ProxyJUnitRunListener {
	static List<RunListener> eventsList = new ArrayList<RunListener>();
	
	public static void register(RunListener runlistener){
		eventsList.add(runlistener);
	}
	
	public static void unregister(RunListener runlistener){
		eventsList.remove(runlistener);
	}
	
	public static List<RunListener> dispatcher(){
		return eventsList;
	}
	
	
}
