package org.sky.auto.exception;

public class NotFoundLoadSourceException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotFoundLoadSourceException(){
		super();
	}
	
	public NotFoundLoadSourceException(String message){
		super(message);
	}
	
	public NotFoundLoadSourceException(String message, Exception e){
		super(message,e);
	}
}
