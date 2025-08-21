package com.example.msFoodCourt.domain.exception;

public class DishCategoryNotFoundException extends RuntimeException {
    public DishCategoryNotFoundException() {
        super("La categoría del plato es obligatoria");
    }
}
