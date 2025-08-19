package com.example.msFoodCourt.infrastructure.input.rest;

import com.example.msFoodCourt.application.dto.RestaurantRequestDto;
import com.example.msFoodCourt.application.dto.RestaurantResponseDto;
import com.example.msFoodCourt.application.handler.IRestaurantHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
@RequiredArgsConstructor
public class RestaurantRestController {

    private final IRestaurantHandler restaurantHandler;

    @PostMapping
    public ResponseEntity<String> createRestaurant(@Valid @RequestBody RestaurantRequestDto restaurantRequest) {
        restaurantHandler.createRestaurant(restaurantRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("Restaurant created successfully");
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
