package com.example.foodcourt.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RestaurantOrderResponse {
    private Long restaurantId;
    private List<OrderDishResponse> dishes;
}
