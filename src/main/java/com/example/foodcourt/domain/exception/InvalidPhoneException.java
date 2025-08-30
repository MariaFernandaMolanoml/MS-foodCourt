package com.example.foodcourt.domain.exception;

public class InvalidPhoneException extends RuntimeException {
    public InvalidPhoneException() {
        super("Invalid phone format");
    }
}