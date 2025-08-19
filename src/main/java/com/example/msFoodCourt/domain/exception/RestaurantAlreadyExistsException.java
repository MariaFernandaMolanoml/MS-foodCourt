package com.example.msFoodCourt.domain.exception;

public class RestaurantAlreadyExistsException extends RuntimeException {
    public RestaurantAlreadyExistsException(String message) {
        super(message);
    }
}