package com.example.foodcourt.domain.spi;

import com.example.foodcourt.domain.model.RestaurantEmployee;

public interface IRestaurantEmployeePersistencePort {
    void saveRestaurantEmployee(RestaurantEmployee restaurantEmployee);
    String findRestaurantNitByEmployeeDocument(String employeeDocument);
}