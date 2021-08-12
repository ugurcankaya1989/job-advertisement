package com.kariyer.jobadvertisement.exception;

public class PhoneNumberRecordedBeforeException extends RuntimeException{
    public PhoneNumberRecordedBeforeException(String message){
        super(message);
    }
}
