package com.example.msFoodCourt.domain.model;


import lombok.Data;

@Data
public class Restaurant {
    private Long id;
    private String name;
    private String nit;
    private String address;
    private String phone;
    private String urlLogo;
    private Long idOwner;
}
