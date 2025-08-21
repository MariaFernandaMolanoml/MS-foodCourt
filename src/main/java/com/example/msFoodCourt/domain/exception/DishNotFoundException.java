package com.example.msFoodCourt.domain.exception;

public class DishNotFoundException extends RuntimeException {
    public DishNotFoundException(String message) {
        super(message);
    }
}
