package com.example.msFoodCourt.infrastructure.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ApiResponse {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private Object message;
}
