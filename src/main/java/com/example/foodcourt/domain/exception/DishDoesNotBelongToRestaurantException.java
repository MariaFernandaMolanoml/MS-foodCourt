package com.example.foodcourt.domain.exception;

public class DishDoesNotBelongToRestaurantException extends RuntimeException {
    public DishDoesNotBelongToRestaurantException(Long dishId, Long restaurantId) {
        super("El plato con id " + dishId + " no pertenece al restaurante con id " + restaurantId);
    }
}