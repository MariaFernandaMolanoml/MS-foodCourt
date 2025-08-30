package com.example.foodcourt.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantEmployeeResponseDto {
    private Long id;
    private String restaurantNit;
    private String employeeDocument;
}