package com.example.foodcourt.domain.exception;

public class OwnerNotFoundException extends RuntimeException {
    public OwnerNotFoundException(String message) {
        super(message);
    }

    public OwnerNotFoundException() {
        super("Owner not found or invalid role");
    }
}