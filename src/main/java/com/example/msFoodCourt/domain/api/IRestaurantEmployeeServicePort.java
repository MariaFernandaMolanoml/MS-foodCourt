package com.example.msFoodCourt.domain.api;

import com.example.msFoodCourt.domain.model.RestaurantEmployee;

public interface IRestaurantEmployeeServicePort {
    void saveRestaurantEmployee(RestaurantEmployee restaurantEmployee, String documentFromToken);
}
