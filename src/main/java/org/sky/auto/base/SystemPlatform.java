package org.sky.auto.base;

import java.util.Properties;



public class SystemPlatform {
	final static Properties props = System.getProperties();
	//final static Set<Object> keySet = System.getProperties().keySet();
	
	public static String getOs(){
		return props.getProperty("os.name");
	}
	
	public String getSystemInfo(String key){
		return props.getProperty(key);
	}
	
	
}	
