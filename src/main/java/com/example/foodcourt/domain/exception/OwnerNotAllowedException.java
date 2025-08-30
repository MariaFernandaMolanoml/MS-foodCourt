package com.example.foodcourt.domain.exception;

public class OwnerNotAllowedException extends RuntimeException {
    public OwnerNotAllowedException(String message) {
        super(message);
    }
}