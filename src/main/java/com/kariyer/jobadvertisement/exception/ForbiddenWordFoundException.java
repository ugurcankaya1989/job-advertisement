package com.kariyer.jobadvertisement.exception;

public class ForbiddenWordFoundException extends RuntimeException{
    public ForbiddenWordFoundException(String message) {
        super(message);
    }
}
