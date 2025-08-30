package com.example.foodcourt.infrastructure.input.rest;

import com.example.foodcourt.application.dto.RestaurantRequestDto;
import com.example.foodcourt.application.dto.RestaurantResponseDto;
import com.example.foodcourt.application.handler.IRestaurantHandler;
import com.example.foodcourt.infrastructure.exception.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/restaurant")
@RequiredArgsConstructor
public class RestaurantRestController {

    private final IRestaurantHandler restaurantHandler;

    @PostMapping
    public ResponseEntity<ApiResponse> createRestaurant(@Valid @RequestBody RestaurantRequestDto restaurantRequest) {
        restaurantHandler.createRestaurant(restaurantRequest);

        ApiResponse response = new ApiResponse(
                LocalDateTime.now(),
                HttpStatus.CREATED.value(),
                "Created",
                "Restaurant created successfully"
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RestaurantResponseDto>> getAllRestaurants() {
        return ResponseEntity.ok(restaurantHandler.getAllRestaurants());
    }

    @GetMapping("/{nit}")
    public ResponseEntity<RestaurantResponseDto> getRestaurantByNit(@PathVariable String nit) {
        return ResponseEntity.ok(restaurantHandler.getRestaurantByNit(nit));
    }
}
