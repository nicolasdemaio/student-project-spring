package com.ndemaio.studentprojectspring.config.exceptions;

public class StudentUnderAgeException extends RuntimeException {

    public StudentUnderAgeException() {
    }

    public StudentUnderAgeException(String message) {
        super(message);
    }
}
