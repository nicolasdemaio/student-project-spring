package com.ndemaio.studentprojectspring.config.exceptions;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ApiException {

    private String message;
    private LocalDateTime timestamp;
    private HttpStatus httpStatus;

    public ApiException(String message, LocalDateTime timestamp, HttpStatus httpStatus) {
        this.message = message;
        this.timestamp = timestamp;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
