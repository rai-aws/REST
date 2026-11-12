package com.spring.spring_rest.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.spring.spring_rest.entity.StudentErrorResponse;
import com.spring.spring_rest.exception.StudentNotFoundException;

/**
 * Student exception handler
 * Provides appropriate HTTP responses for different exceptions.
 */
@ControllerAdvice 
public class StudentExceptionHandler {

    /**
     * Handles exceptions when a requested student record is not found.
     *
     * @param ex the exception containing the error message
     * @return a response containing the error details and HTTP 404 status
     */
    @ExceptionHandler 
    public ResponseEntity<StudentErrorResponse> handleException(
                                                StudentNotFoundException ex){
        StudentErrorResponse err = new StudentErrorResponse();

        err.setStatus(HttpStatus.NOT_FOUND.value());
        err.setMessage(ex.getMessage());
        err.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles general exceptions that occur during request processing.
     *
     * @param ex the exception that occurred
     * @return a response containing the error details and HTTP 400 status
     */
    @ExceptionHandler 
    public ResponseEntity<StudentErrorResponse> handleException(Exception ex){
        StudentErrorResponse err = new StudentErrorResponse();

        err.setStatus(HttpStatus.BAD_REQUEST.value());
        err.setMessage("Invalid Data. Only integers are accepted");
        err.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles validation errors when request body fields
     * fail their configured validation constraints.
     *
     * @param ex the exception containing the validation errors
     * @return a map of field names and their corresponding error messages
     *         with HTTP 400 status
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(
        MethodArgumentNotValidException ex) {

            Map<String, String> err = new HashMap<>();

            ex.getBindingResult().getFieldErrors().forEach(error -> {
                err.put(error.getField(), error.getDefaultMessage());
            } );

            return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);        
    }

}