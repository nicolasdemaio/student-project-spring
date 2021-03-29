package com.ndemaio.studentprojectspring.config.exceptions;

public class EmailNotAvailableException extends RuntimeException {

    public EmailNotAvailableException() {
    }

    public EmailNotAvailableException(String message) {
        super(message);
    }
}
