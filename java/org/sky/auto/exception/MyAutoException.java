package org.sky.auto.exception;


public class MyAutoException  extends UnsupportedOperationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Exception e;

	public MyAutoException() {

		//AutoBase.closeAllWindow();

	}

	public MyAutoException(String message) {

		super(message);

		//AutoBase.closeAllWindow();

		dealException();
	}

	public MyAutoException(String message, Exception e) {

		super(message);

		this.e = e;

		dealException();

	}

	public void dealException() {

		if (this.e != null) {

			this.e.printStackTrace();
		}
	}

}
