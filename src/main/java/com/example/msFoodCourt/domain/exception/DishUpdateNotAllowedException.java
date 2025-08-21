package com.example.msFoodCourt.domain.exception;

public class DishUpdateNotAllowedException extends RuntimeException {
    public DishUpdateNotAllowedException(String s) {
        super("Solo se permite modificar el precio y la descripción del plato.");
    }
}
