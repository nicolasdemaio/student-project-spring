package com.ndemaio.studentprojectspring.student.exceptions;

public class EmailNotAvailableException extends RuntimeException {

    public EmailNotAvailableException() {
    }

    public EmailNotAvailableException(String message) {
        super(message);
    }
}
