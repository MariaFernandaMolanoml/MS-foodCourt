package com.example.msFoodCourt.domain.exception;

public class UpdateDishException extends RuntimeException {
    public UpdateDishException() {
        super("Debe enviar al menos un campo para actualizar (precio o descripción)");
    }
}
