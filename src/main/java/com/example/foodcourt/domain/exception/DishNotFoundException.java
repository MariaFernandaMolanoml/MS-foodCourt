package com.example.foodcourt.domain.exception;

public class DishNotFoundException extends RuntimeException {
    public DishNotFoundException() {
        super("Dish not found");
    }

    public DishNotFoundException(String message) {
        super(message);
    }
}
