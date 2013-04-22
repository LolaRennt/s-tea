package org.sky.auto.result;

import org.junit.runner.Description;
import org.junit.runner.notification.Failure;

public class MyFailure {
	private Failure failure;
	public MyFailure(Failure failure){
		this.failure=failure;
	}
	
	protected Failure getFailure(){
		return failure;
	}
	
	public Description getFailureDescription(){
		return getFailure().getDescription();
	}
	
	
	public Throwable getException(){
		return getFailure().getException();
	}
	
	public String getMessage(){
		return getFailure().getMessage();
	}
	
	public String getTrace(){
		return getFailure().getTrace();
	}
}
