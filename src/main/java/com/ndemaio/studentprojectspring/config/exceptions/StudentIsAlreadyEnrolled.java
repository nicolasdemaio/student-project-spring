package com.ndemaio.studentprojectspring.config.exceptions;

public class StudentIsAlreadyEnrolled extends RuntimeException {

    public StudentIsAlreadyEnrolled() {
    }

    public StudentIsAlreadyEnrolled(String message) {
        super(message);
    }
}
