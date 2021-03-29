package com.ndemaio.studentprojectspring.student.exceptions;

public class StudentUnderAgeException extends RuntimeException {

    public StudentUnderAgeException() {
    }

    public StudentUnderAgeException(String message) {
        super(message);
    }
}
