package com.spring.spring_rest.exception;

/**
 * Exception thrown when a requested student record cannot be found.
 * Extends RuntimeException to support unchecked exception handling.
 */
public class StudentNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs an exception with a detail message and cause.
     *
     * @param message the detail message describing the exception
     * @param cause the underlying cause of the exception
     */
    public StudentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs an exception with a detail message.
     *
     * @param message the detail message describing the exception
     */
    public StudentNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs an exception with an underlying cause.
     *
     * @param cause the underlying cause of the exception
     */
    public StudentNotFoundException(Throwable cause) {
        super(cause);
    } 

}