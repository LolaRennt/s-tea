package org.sky.auto.exception;

public class SameIDNameException extends RuntimeException{

	/**
	 * 当我们的资源中存在相同的id名字的时候，加载会报处异常
	 * @author 王天庆
	 */
	private static final long serialVersionUID = 1L;
	
	public SameIDNameException() {
	}
	
	public SameIDNameException(String message){
		super(message);
	}

}
