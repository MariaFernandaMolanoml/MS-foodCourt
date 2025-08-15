package com.example.msFoodCourt.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String document;
    private String phone;
    private String password;
    private String role;
}
