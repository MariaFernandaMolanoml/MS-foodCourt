package com.example.msFoodCourt.infrastructure.input.rest;

import com.example.msFoodCourt.application.dto.RestaurantEmployeeRequestDto;
import com.example.msFoodCourt.application.handler.IRestaurantEmployeeHandler;
import com.example.msFoodCourt.infrastructure.exception.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;

@RestController
@RequestMapping("/restaurant-employee")
@RequiredArgsConstructor
public class RestaurantEmployeeRestController {

    private final IRestaurantEmployeeHandler restaurantEmployeeHandler;

    @PostMapping
    public ResponseEntity<ApiResponse> saveRestaurantEmployee(@RequestBody RestaurantEmployeeRequestDto requestDto) {
        restaurantEmployeeHandler.saveRestaurantEmployee(requestDto);

        ApiResponse response = new ApiResponse(
                java.time.LocalDateTime.now(),
                HttpStatus.CREATED.value(),
                HttpStatus.CREATED.getReasonPhrase(),
                "Restaurant employee created successfully"
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
