package com.kariyer.jobadvertisement.exception;

public class DontHaveTheRightToPostAnAdvException extends RuntimeException{
    public DontHaveTheRightToPostAnAdvException(String message) {
        super(message);
    }
}
