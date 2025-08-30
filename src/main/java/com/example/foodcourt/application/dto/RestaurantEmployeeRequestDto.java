package com.example.foodcourt.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantEmployeeRequestDto {
    private Long id;
    private String employeeDocument;
    private String restaurantNit;
}