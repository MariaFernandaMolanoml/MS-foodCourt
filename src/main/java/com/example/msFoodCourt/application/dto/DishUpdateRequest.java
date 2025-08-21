package com.example.msFoodCourt.application.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = false)
public class DishUpdateRequest {
    private String description;
    private Double price;
}
