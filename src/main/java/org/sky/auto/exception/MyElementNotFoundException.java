package org.sky.auto.exception;

import org.openqa.selenium.NoSuchElementException;

public class MyElementNotFoundException extends NoSuchElementException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MyElementNotFoundException(String reason) {
		super(reason);
	}

}
