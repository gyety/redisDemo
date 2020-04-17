package com.exception;

public class RedissClientException extends RuntimeException {
    private String errormsg;
    public RedissClientException(String message){
        this.errormsg=message;
    }
}
