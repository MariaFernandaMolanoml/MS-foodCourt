package com.example.foodcourt.domain.exception;

public class InvalidDishQuantityException extends RuntimeException {
    public InvalidDishQuantityException() {
        super("La cantidad de platos debe ser positiva");
    }
}