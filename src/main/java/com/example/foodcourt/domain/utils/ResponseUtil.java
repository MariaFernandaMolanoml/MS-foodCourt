package com.example.foodcourt.domain.utils;

import com.example.foodcourt.infrastructure.exception.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public final class ResponseUtil {

    private ResponseUtil() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    public static ResponseEntity<ApiResponse> success(String message) {
        ApiResponse response = new ApiResponse(
                LocalDateTime.now(),
                HttpStatus.CREATED.value(),
                "Created",
                message
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    public static ResponseEntity<ApiResponse> ok(String message) {
        ApiResponse response = new ApiResponse(
                LocalDateTime.now(),
                HttpStatus.OK.value(),
                "OK",
                message
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public static ResponseEntity<ApiResponse> error(String message, HttpStatus status) {
        ApiResponse response = new ApiResponse(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                message
        );
        return new ResponseEntity<>(response, status);
    }
}
