package com.chzu.exception;

/**
 * 系统自定义全局异常类，针对预期异常，需要在程序中抛出此类的异常
 */
public class Globalexception extends Exception {


    //异常信息
    public String message;

    public Globalexception(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
