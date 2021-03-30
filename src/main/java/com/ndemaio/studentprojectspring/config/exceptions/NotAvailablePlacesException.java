package com.ndemaio.studentprojectspring.config.exceptions;

public class NotAvailablePlacesException extends RuntimeException {

    public NotAvailablePlacesException() {
    }

    public NotAvailablePlacesException(String message) {
        super(message);
    }

}
