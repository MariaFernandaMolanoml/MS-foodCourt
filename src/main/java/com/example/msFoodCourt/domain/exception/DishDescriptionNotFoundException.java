package com.example.msFoodCourt.domain.exception;

public class DishDescriptionNotFoundException extends RuntimeException {
    public DishDescriptionNotFoundException() {
        super("La descripción del plato es obligatoria");
    }
}
