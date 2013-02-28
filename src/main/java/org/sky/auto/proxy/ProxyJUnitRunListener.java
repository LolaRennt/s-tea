package org.sky.auto.proxy;

import java.util.ArrayList;
import java.util.List;

import org.junit.runner.notification.RunListener;



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
