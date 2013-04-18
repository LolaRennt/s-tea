package org.sky.auto.exception;

public class NoSuchIDElementException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public NoSuchIDElementException() {
		super();
	}
	
	public NoSuchIDElementException(String message){
		super(message);
	}
	
	public NoSuchIDElementException(String message,Exception e){
		super(message,e);
	}

}
