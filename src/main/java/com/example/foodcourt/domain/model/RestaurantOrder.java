package com.example.foodcourt.domain.model;

import java.util.List;
import lombok.Data;

@Data
public class RestaurantOrder {
    private Long restaurantId;
    private List<OrderDish> dishes;
}
