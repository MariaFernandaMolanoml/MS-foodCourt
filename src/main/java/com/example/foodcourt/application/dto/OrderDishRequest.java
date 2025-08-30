package com.example.foodcourt.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDishRequest {
    private Long dishId;
    private Integer quantity;
}