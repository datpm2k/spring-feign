package com.tad.springfeign.exception;

public class SystemErrorException extends RuntimeException {

    public SystemErrorException(String message) {
        super(message);
    }
}
