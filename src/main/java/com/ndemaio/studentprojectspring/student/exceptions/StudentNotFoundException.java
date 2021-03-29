package com.ndemaio.studentprojectspring.student.exceptions;

public class StudentNotFoundException extends RuntimeException {

    public StudentNotFoundException() {
    }

    public StudentNotFoundException(String message) {
        super(message);
    }
}