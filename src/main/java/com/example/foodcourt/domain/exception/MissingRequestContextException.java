package com.example.foodcourt.domain.exception;

public class MissingRequestContextException extends RuntimeException {
    public MissingRequestContextException(String message) {
        super(message);
    }
}
