package com.victor.exception;

public class ResourceAlreadyExistInDataBaseException extends RuntimeException{

    public ResourceAlreadyExistInDataBaseException(String message){
        super(message);
    }
}
