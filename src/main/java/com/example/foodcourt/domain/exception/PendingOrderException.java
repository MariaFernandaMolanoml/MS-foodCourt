package com.example.foodcourt.domain.exception;

public class PendingOrderException extends RuntimeException {
    public PendingOrderException() {
        super("El cliente ya tiene un pedido activo");
    }
}
