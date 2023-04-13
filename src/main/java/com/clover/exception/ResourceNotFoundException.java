package com.clover.exception;

public class ResourceNotFoundException extends RuntimeException {

    String msg;

    public ResourceNotFoundException( String msg) {
        super(String.format(msg));
        this.msg = msg;
    }
}
