package com.example.foodcourt.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class OrderResponse {
    private Long id;
    private String clientDocument;
    private LocalDate date;
    private String status;
    private String employeeDocument;
    private List<RestaurantOrderResponse> restaurants;
}
