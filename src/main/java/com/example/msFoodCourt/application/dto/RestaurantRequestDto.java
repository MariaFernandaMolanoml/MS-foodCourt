package com.example.msFoodCourt.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class RestaurantRequestDto {

    private Long id;

    @NotBlank(message = "First name is required")
    private String name;

    @NotBlank
    @Pattern(regexp = "\\d+", message = "NIT must be numeric")
    private String nit;

    @NotBlank
    private String address;

    @NotBlank
    @Pattern(regexp = "^\\+?\\d{1,13}$", message = "Phone must be numeric and max 13 digits, may include '+'")
    private String phone;

    @NotBlank
    private String urlLogo;

    @NotNull
    private Long idOwner;
}
