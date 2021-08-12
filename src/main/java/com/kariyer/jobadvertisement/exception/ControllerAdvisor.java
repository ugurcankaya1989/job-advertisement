package com.kariyer.jobadvertisement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(PhoneNumberRecordedBeforeException.class)
    public ResponseEntity<ExceptionResponse> phoneNumberRecordedBefore(PhoneNumberRecordedBeforeException phoneNumberRecordedBeforeException){
        ExceptionResponse exceptionResponse = ExceptionResponse.builder().errorMessage(phoneNumberRecordedBeforeException.getMessage()).errorCode("").dateTime(LocalDateTime.now()).build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(DontHaveTheRightToPostAnAdvException.class)
    public ResponseEntity<ExceptionResponse> doNotHaveTheRightToPostAnAdv(DontHaveTheRightToPostAnAdvException dontHaveTheRightToPostAnAdvException){
        ExceptionResponse exceptionResponse = ExceptionResponse.builder().errorMessage(dontHaveTheRightToPostAnAdvException.getMessage()).errorCode("").dateTime(LocalDateTime.now()).build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<ExceptionResponse> recordNotFound(RecordNotFoundException recordNotFoundException) {
        ExceptionResponse response = new ExceptionResponse(recordNotFoundException.getMessage(), "", LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotSavedException.class)
    public ResponseEntity<ExceptionResponse> notSaved(NotSavedException notSavedException) {
        ExceptionResponse response = new ExceptionResponse(notSavedException.getMessage(), "", LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.NOT_IMPLEMENTED);
    }


}
