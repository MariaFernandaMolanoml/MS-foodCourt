package com.example.foodcourt.domain.exception;

public class InvalidNitException extends RuntimeException {
    public InvalidNitException() {
        super("NIT must be numeric");
    }

    public InvalidNitException(String field) {
        super(field + " must be numeric");
    }
}