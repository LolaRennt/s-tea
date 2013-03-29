package org.sky.auto.base;

import java.util.Properties;


/**这个类里面提供了一个判断系统的简单工具*/
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
