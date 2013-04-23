package org.sky.auto.result;

import org.junit.runner.Description;
import org.junit.runner.notification.Failure;

public class MyFailure {
	private Failure failure;
	private Throwable exception;
	private String message;
	private String trace;
	public MyFailure(Failure failure){
		this.failure=failure;
		this.exception=failure.getException();
		this.message=failure.getMessage();
		this.trace=failure.getTrace();
	}
	
	public void setFailure(Failure failure) {
		this.failure = failure;
	}

	public void setException(Throwable exception) {
		this.exception = exception;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setTrace(String trace) {
		this.trace = trace;
	}

	protected Failure getFailure(){
		return failure;
	}
	
	public Description getFailureDescription(){
		return getFailure().getDescription();
	}
	
	
	public Throwable getException(){
		return this.exception;
	}
	
	public String getMessage(){
		return this.message;
	}
	
	public String getTrace(){
		return this.trace;
	}
}
