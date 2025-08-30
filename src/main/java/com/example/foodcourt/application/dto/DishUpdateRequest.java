package com.example.foodcourt.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class DishUpdateRequest {
    private String description;
    private Double price;
    private Boolean active;
}
