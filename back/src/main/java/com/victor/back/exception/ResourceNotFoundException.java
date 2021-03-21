package com.victor.back.exception;

public class ResourceNotFoundException  extends RuntimeException {

    public ResourceNotFoundException(String message){
        super(message);
    }
}
