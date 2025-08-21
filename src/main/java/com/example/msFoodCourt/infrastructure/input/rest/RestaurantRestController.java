package com.example.msFoodCourt.infrastructure.input.rest;

import com.example.msFoodCourt.application.dto.RestaurantRequestDto;
import com.example.msFoodCourt.application.dto.RestaurantResponseDto;
import com.example.msFoodCourt.application.handler.IRestaurantHandler;
import com.example.msFoodCourt.infrastructure.exception.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
