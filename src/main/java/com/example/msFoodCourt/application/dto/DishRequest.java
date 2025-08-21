package com.example.msFoodCourt.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DishRequest {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String urlPhoto;
    private Long restaurantId;
    private String categoryName;
    private Boolean active;
}