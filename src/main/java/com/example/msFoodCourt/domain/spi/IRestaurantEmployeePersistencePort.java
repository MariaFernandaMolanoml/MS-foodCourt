package com.example.msFoodCourt.domain.spi;

import com.example.msFoodCourt.domain.model.RestaurantEmployee;

public interface IRestaurantEmployeePersistencePort {
    void saveRestaurantEmployee(RestaurantEmployee restaurantEmployee);
}