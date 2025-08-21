package com.example.msFoodCourt.domain.utils;

import com.example.msFoodCourt.infrastructure.exception.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.time.LocalDateTime;

public class ResponseUtil {

    public static ResponseEntity<ApiResponse> success(String message) {
        ApiResponse response = new ApiResponse(
                LocalDateTime.now(),
                HttpStatus.CREATED.value(),
                "Created",
                message
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
