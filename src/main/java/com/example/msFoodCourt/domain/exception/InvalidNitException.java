package com.example.msFoodCourt.domain.exception;

public class InvalidNitException extends RuntimeException {
    public InvalidNitException(String message) {
        super(message);
    }
}