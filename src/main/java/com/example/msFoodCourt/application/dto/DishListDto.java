package com.example.msFoodCourt.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DishListDto {
    private String name;
    private String description;
    private Integer price;
    private String category;
}
