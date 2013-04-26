package org.sky.auto.result;

import java.lang.annotation.Annotation;
import java.util.Collection;

import org.junit.runner.Description;
import org.sky.auto.mail.date.SimpleDate;

public class MyDescription {
	private Description description;
	private String methodName;
	private Class<?> testClass;
	private Collection<Annotation> annotations;
	private int testCount;
	private String time;
	private MyFailure failure;
	public void setFailure(MyFailure failure) {
		this.failure = failure;
	}


	private boolean failureCase;
	public Description getDescription() {
		return description;
	}


	public void setDescription(Description des) {
		this.description = des;
	}


	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}


	public void setTestClass(Class<?> testClass) {
		this.testClass = testClass;
	}


	public void setAnnotations(Collection<Annotation> annotations) {
		this.annotations = annotations;
	}


	public void setTestCount(int testCount) {
		this.testCount = testCount;
	}


	public void setTime(String time) {
		this.time = time;
	}


	public void setFailureCase(boolean failureCase) {
		this.failureCase = failureCase;
	}


	public MyDescription(Description des){
		this.description=des;
		this.annotations=des.getAnnotations();
		this.testClass=des.getTestClass();
		this.methodName=des.getMethodName();
		this.time=SimpleDate.getSimpleDateFormat();
		this.testCount=des.testCount();
		this.failureCase=false;
	}
	
	
	public String getMethodName(){
		return this.methodName;
	}

	
	
	public <T extends Annotation> T getAnnotation(Class<T> annotationType){
		return description.getAnnotation(annotationType);
	}
	
	public Collection<Annotation> getAnnotations(){
		return this.annotations;
	}
	
	public Class<?> getTestClass(){
		return this.testClass;
	}
	
	public int getTestCount(){
		return this.testCount;
	}
	
	public boolean isEmpty(){
		return description.isEmpty();
	}
	public boolean isSuit(){
		return description.isSuite();
	}
	
	public boolean isTest(){
		return description.isTest();
	}
	
	public boolean isFailureCase(){
		return failureCase;
	}
	
	public MyFailure getFailure(){
		return failure;
	}
	
	
	public String getTime(){
		this.time= SimpleDate.getSimpleDateFormat();
		return this.time;
	}
}
