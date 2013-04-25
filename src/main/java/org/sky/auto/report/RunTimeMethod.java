package org.sky.auto.report;

import java.lang.reflect.Method;

import org.junit.Test;

public class RunTimeMethod {
	static String name="Main";
	public static String getMethodName(){
		StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
		for(int i=0;i<stacks.length;i++){
			try {
				Class<?> clazz=Class.forName(stacks[i].getClassName());
				Method[] methods=clazz.getDeclaredMethods();
				for(Method m:methods){
					if(m.getName().equals(stacks[i].getMethodName())){
						if(m.isAnnotationPresent(Test.class)){
							String className = stacks[i].getClassName();
							name= "Case:"+className.substring(className.lastIndexOf(".")+1, className.length())+"=>"+stacks[i].getMethodName();
							return name;
						}
						
					}
				}
				i++;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		return null;
	}
	
	public static String getName(){
		return name;
	}
	
	protected static String getSimpleMethodName(){
		StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
		for(int i=0;i<stacks.length;i++){
			try {
				Class<?> clazz=Class.forName(stacks[i].getClassName());
				Method[] methods=clazz.getDeclaredMethods();
				for(Method m:methods){
					if(m.getName().equals(stacks[i].getMethodName())){
						if(m.isAnnotationPresent(Test.class)){
							name= stacks[i].getMethodName();
							return name;
						}
						
					}
				}
				i++;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		return null;
	}
}
