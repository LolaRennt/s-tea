package com.github.lmm.exception;

/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-6-28
 * Time: 上午10:48
 * To change this template use File | Settings | File Templates.
 */
public class ContorlError extends RuntimeException {

    public ContorlError(){
        super();
    }

    public ContorlError(String message){
        super(message);
    }

    public ContorlError(String message,Exception e){
        super(message,e);
    }
}
