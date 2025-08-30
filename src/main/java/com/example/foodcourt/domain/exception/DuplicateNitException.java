package com.example.foodcourt.domain.exception;

public class DuplicateNitException extends RuntimeException {
    public DuplicateNitException() {
        super("A restaurant with this NIT already exists");
    }

    public DuplicateNitException(String message) {
        super(message);
    }
}