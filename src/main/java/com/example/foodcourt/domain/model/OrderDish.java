package com.example.foodcourt.domain.model;

import lombok.Data;

@Data
public class OrderDish {
    private Long dishId;
    private String dishName;
    private Integer quantity;
}


