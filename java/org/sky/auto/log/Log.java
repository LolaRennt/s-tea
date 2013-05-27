package org.sky.auto.log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {
	public static void Debug(String message){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E");
		System.out.println("[DEBUG]-"+df.format(new Date())+"-[thread-"+Thread.currentThread().getId()+"]"+"-->"+message);
	}
	
	public static void INFO(String message){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E");
		System.out.println("[INFO]-"+df.format(new Date())+"-[thread-"+Thread.currentThread().getId()+"]"+"-->"+message);
	}
	public static void WARN(String message){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E");
		System.out.println("[WARN]-"+df.format(new Date())+"-[thread-"+Thread.currentThread().getId()+"]"+"-->"+message);
	}
	public static void ERROR(String message){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E");
		System.out.println("[ERROR]-"+df.format(new Date())+"-[thread-"+Thread.currentThread().getId()+"]"+"-->"+message);
	}
}
