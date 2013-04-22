package org.sky.auto.result;


import java.util.HashSet;
import java.util.Set;

import org.junit.runner.Description;

public class CaseStatus {
	static public Set<Class<?>> noruncls = new HashSet<Class<?>>();
	static public Set<Class<?>> haveruncls = new HashSet<Class<?>>();
	static public Set<MyDescription> haverunclsDes = new HashSet<MyDescription>();
	static public Set<MyFailure> errorcls = new HashSet<MyFailure>();
	public static void addRunClass(Class<?>clazz){
		haveruncls.add(clazz);
		noruncls.remove(clazz);
	}
	
	public static void addNotRunClass(Class<?>clazz){
		noruncls.add(clazz);
	}
	
	
	public static void clear(){
		noruncls.clear();
		haveruncls.clear();
	}
	
	public static void addRunClassDescription(MyDescription des){
		haverunclsDes.add(des);
		noruncls.remove(des.getTestClass());
	}
	
	public static void addRunClassDescription(Description des){
		haverunclsDes.add(new MyDescription(des));
		noruncls.remove(des.getTestClass());
	}

	public static void addFailureClass(MyFailure failure){
		errorcls.add(failure);
		noruncls.remove(failure.getFailure().getDescription().getTestClass());
	}

	
	public static Set<Class<?>> getRunClasses(){
		return haveruncls;
	}
	
	public static Set<Class<?>> getNotRunClasses(){
		return noruncls;
	}
	
	public static Set<MyDescription> getDescriptionClasses(){
		return haverunclsDes;
	}
	
	public static Set<MyFailure> getFailureClasses(){
		return errorcls;
	}
	
	public static void setNotRunClasses(Set<Class<?>>cls){
		noruncls.addAll(cls);
	}
	
	
}
