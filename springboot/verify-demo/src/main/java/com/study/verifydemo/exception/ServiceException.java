package com.study.verifydemo.exception;

public class ServiceException extends RuntimeException {
    public ServiceException() {
        super();
    }

    public ServiceException(String msg) {
        super(msg);
    }
}
