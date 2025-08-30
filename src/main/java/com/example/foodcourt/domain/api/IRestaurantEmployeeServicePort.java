package com.example.foodcourt.domain.api;

import com.example.foodcourt.domain.model.RestaurantEmployee;

public interface IRestaurantEmployeeServicePort {
    void saveRestaurantEmployee(RestaurantEmployee restaurantEmployee, String documentFromToken);
}
