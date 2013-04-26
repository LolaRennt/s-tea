package org.sky.auto.report;

import java.lang.reflect.Method;

import org.junit.Test;

public class RunTimeMethod {
	static ThreadLocal<String> mname = new ThreadLocal<String>(){
		String name="Main";
		protected String initialValue() {	
			return name;
		};
		
		@SuppressWarnings("unused")
		protected void setName(String string){
			this.name=string;
		}
	};
	//static String name="Main";
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
						mname.set("Case:"+className.substring(className.lastIndexOf(".")+1, className.length())+"=>"+stacks[i].getMethodName());
							return mname.get();
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
		return mname.get();
	}

}
