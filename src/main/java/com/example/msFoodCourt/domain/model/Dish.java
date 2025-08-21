package com.example.msFoodCourt.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Dish {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String urlPhoto;
    private Long restaurantId;
    private Long categoryId;
    private Boolean active;
}
