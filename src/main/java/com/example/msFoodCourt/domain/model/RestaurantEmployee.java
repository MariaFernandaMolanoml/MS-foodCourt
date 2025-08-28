package com.example.msFoodCourt.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantEmployee {
    private Long id;
    private String restaurantNit;
    private String employeeDocument;
}