package org.sky.auto.result;

import java.lang.annotation.Annotation;
import java.util.Collection;

import org.junit.runner.Description;

public class MyDescription {
	private Description des;
	
	public MyDescription(Description des){
		this.des=des;
	}
	
	
	public String getMethodName(){
		return des.getMethodName();
	}
	
	public String getClassName(){
		return des.getClassName();
	}
	
	
	public <T extends Annotation> T getAnnotation(Class<T> annotationType){
		return des.getAnnotation(annotationType);
	}
	
	public Collection<Annotation> getAnnotations(){
		return des.getAnnotations();
	}
	
	public Class<?> getTestClass(){
		return des.getTestClass();
	}
	
	public int getTestCount(){
		return des.testCount();
	}
	
	public boolean isEmpty(){
		return des.isEmpty();
	}
	public boolean isSuit(){
		return des.isSuite();
	}
	
	public boolean isTest(){
		return des.isTest();
	}
	
	public boolean isFailureCase(){
		for(MyFailure clazz:CaseStatus.getFailureCases()){
			if(clazz.getFailureDescription().getMethodName().equals(getMethodName())){
				return true;
			}
		}
		return false;
	}
	
	public MyFailure getFailure(){
		for(MyFailure clazz:CaseStatus.getFailureCases()){
			if(clazz.getFailureDescription().getTestClass().getName().equals(getTestClass().getName())){
				return clazz;
			}
		}
		return null;
	}
}
