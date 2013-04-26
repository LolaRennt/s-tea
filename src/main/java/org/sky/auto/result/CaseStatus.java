package org.sky.auto.result;


import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import org.junit.runner.Description;

public class CaseStatus {
	static public Set<Method> noruncls = new HashSet<Method>();
	static public Set<Method> haveruncls = new HashSet<Method>();
	static public Set<MyDescription> haverunclsDes = new HashSet<MyDescription>();
	static public Set<MyFailure> errorcls = new HashSet<MyFailure>();
	public static void addRunCase(Method method){
		haveruncls.add(method);
		noruncls.remove(method);
	}
	
	public static void addNotRunCase(Method method){
		noruncls.add(method);
	}
	
	
	public static void clear(){
		noruncls.clear();
		haveruncls.clear();
	}
	
	public static void addRunCaseDescription(MyDescription des){
		haverunclsDes.add(des);
		noruncls.remove(des.getTestClass());
	}
	
	public static void addRunCaseDescription(Description des){
		haverunclsDes.add(new MyDescription(des));
		noruncls.remove(des.getTestClass());
	}

	public static void addFailureCase(MyFailure failure){
		errorcls.add(failure);
		noruncls.remove(failure.getFailure().getDescription().getTestClass());
	}

	
	public static Set<Method> getRunCases(){
		return haveruncls;
	}
	
	public static Set<Method> getNotRunCases(){
		return noruncls;
	}
	
	public static Set<MyDescription> getDescriptionCases(){
		return haverunclsDes;
	}
	
	public static Set<MyFailure> getFailureCases(){
		return errorcls;
	}
	
	public static void setNotRunCases(Set<Method>methods){
		noruncls.addAll(methods);
	}
	
	public static void removeRunCaseDescription(MyDescription des){
		haverunclsDes.remove(des);
	}
	
	
}
