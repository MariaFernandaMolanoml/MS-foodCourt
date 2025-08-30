package com.example.foodcourt.infrastructure.input.rest;

import com.example.foodcourt.application.dto.RestaurantEmployeeRequestDto;
import com.example.foodcourt.application.handler.IRestaurantEmployeeHandler;
import com.example.foodcourt.infrastructure.exception.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
