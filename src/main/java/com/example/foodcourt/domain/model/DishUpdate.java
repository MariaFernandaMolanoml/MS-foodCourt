package com.example.foodcourt.domain.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DishUpdate {
    private Long id;
    private String description;
    private Double price;
    private Boolean active;
}
