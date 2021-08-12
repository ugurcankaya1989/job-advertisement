package com.kariyer.jobadvertisement.exception;

public class RecordNotFoundException extends RuntimeException{
    public RecordNotFoundException(String message){
        super(message);
    }
}
