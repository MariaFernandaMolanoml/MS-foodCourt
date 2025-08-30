package com.example.foodcourt.domain.exception;

public class MissingFieldsException extends RuntimeException {
    public MissingFieldsException() {
        super("All fields are required");
    }
}