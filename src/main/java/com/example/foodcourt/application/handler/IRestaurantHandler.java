package com.example.foodcourt.application.handler;

import com.example.foodcourt.application.dto.RestaurantRequestDto;
import com.example.foodcourt.application.dto.RestaurantResponseDto;
import java.util.List;

public interface IRestaurantHandler {

    void createRestaurant(RestaurantRequestDto restaurantRequestDto);

    List<RestaurantResponseDto> getAllRestaurants();

    RestaurantResponseDto getRestaurantByNit(String nit);
}
