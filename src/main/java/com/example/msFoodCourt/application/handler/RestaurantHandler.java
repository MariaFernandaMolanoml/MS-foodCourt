package com.example.msFoodCourt.application.handler;

import com.example.msFoodCourt.application.dto.RestaurantRequestDto;
import com.example.msFoodCourt.application.dto.RestaurantResponseDto;
import com.example.msFoodCourt.application.mapper.RestaurantRequestMapper;
import com.example.msFoodCourt.domain.api.IRestaurantServicePort;
import com.example.msFoodCourt.domain.model.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantHandler implements IRestaurantHandler {

    private final IRestaurantServicePort restaurantServicePort;
    private final RestaurantRequestMapper restaurantRequestMapper;

    @Override
    public void createRestaurant(RestaurantRequestDto restaurantRequestDto) {
        Restaurant restaurant = restaurantRequestMapper.toRestaurant(restaurantRequestDto);
        restaurantServicePort.createRestaurant(restaurant);
    }

    @Override
    public List<RestaurantResponseDto> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantServicePort.getAllRestaurants();
        return restaurantRequestMapper.toResponseList(restaurants);
    }

    @Override
    public RestaurantResponseDto getRestaurantById(Long id) {
        Restaurant restaurant = restaurantServicePort.getRestaurantById(id);
        return restaurantRequestMapper.toResponse(restaurant);
    }
}