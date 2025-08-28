package com.example.msFoodCourt.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantEmployeeRequestDto {
    private Long id;
    private String employeeDocument;
    private String restaurantNit;
}