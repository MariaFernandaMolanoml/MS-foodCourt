package com.example.foodcourt.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RestaurantOrderRequest {
    private Long restaurantId;
    private List<OrderDishRequest> dishes;
}