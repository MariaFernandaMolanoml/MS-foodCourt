package com.example.msFoodCourt.domain.api;

import com.example.msFoodCourt.domain.model.Restaurant;

import java.util.List;

public interface IRestaurantServicePort {
    void createRestaurant(Restaurant restaurant);

    List<Restaurant> getAllRestaurants();

    Restaurant getRestaurantById(Long id);
}