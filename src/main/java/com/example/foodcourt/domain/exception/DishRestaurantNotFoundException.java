package com.example.foodcourt.domain.exception;

public class DishRestaurantNotFoundException extends RuntimeException {
    public DishRestaurantNotFoundException() {
        super("El plato debe estar asociado a un restaurante");
    }
}
