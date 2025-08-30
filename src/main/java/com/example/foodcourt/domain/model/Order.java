package com.example.foodcourt.domain.model;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class Order {
    private Long id;
    private String clientDocument;
    private LocalDate date;
    private String status;
    private String employeeDocument;
    private List<RestaurantOrder> restaurantOrders;
}
