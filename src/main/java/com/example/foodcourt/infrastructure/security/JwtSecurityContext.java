package com.example.foodcourt.infrastructure.security;

import lombok.Data;
import org.springframework.security.core.context.SecurityContextHolder;

@Data
public class JwtSecurityContext {
    private String document;
    private String jwt;

    public static JwtSecurityContext getContext() {
        return (JwtSecurityContext) SecurityContextHolder.getContext().getAuthentication().getDetails();
    }

}
