package com.example.msFoodCourt.application.handler;

import com.example.msFoodCourt.application.dto.RestaurantRequestDto;
import com.example.msFoodCourt.application.dto.RestaurantResponseDto;
import java.util.List;

public interface IRestaurantHandler {

    void createRestaurant(RestaurantRequestDto restaurantRequestDto);

    List<RestaurantResponseDto> getAllRestaurants();

    RestaurantResponseDto getRestaurantByNit(String nit);
}
