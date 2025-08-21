package com.example.msFoodCourt.domain.exception;

public class DishPriceNotValidException extends RuntimeException {
    public DishPriceNotValidException() {
        super("El precio del plato debe ser mayor a 0");
    }
}