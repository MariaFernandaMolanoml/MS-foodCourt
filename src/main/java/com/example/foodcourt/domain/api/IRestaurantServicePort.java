package com.example.foodcourt.domain.api;

import com.example.foodcourt.domain.model.Restaurant;
import java.util.List;

public interface IRestaurantServicePort {
    void createRestaurant(Restaurant restaurant);

    List<Restaurant> getAllRestaurants();

    Restaurant getRestaurantByNit(String nit);

}