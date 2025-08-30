package com.example.foodcourt.domain.exception;

public class InvalidNameException extends RuntimeException {
    public InvalidNameException() {
        super("The name cannot contain only numbers");
    }
}