package com.example.foodcourt.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDishResponse {
    private Long dishId;
    private String dishName;
    private Integer quantity;
}