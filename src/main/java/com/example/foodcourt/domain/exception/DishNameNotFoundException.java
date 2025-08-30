package com.example.foodcourt.domain.exception;

public class DishNameNotFoundException extends RuntimeException {
    public DishNameNotFoundException() {
        super("The name of the dish is required");
    }
}