package com.example.msFoodCourt.infrastructure.input.rest;

import com.example.msFoodCourt.application.dto.FoodCourtListDto;
import com.example.msFoodCourt.application.dto.DishListDto;
import com.example.msFoodCourt.application.handler.FoodCourtHandler;
import com.example.msFoodCourt.application.handler.IDishHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
@RequiredArgsConstructor
public class FoodCourtController {

    private final FoodCourtHandler foodCourtHandler;
    private final IDishHandler dishHandler;

    @GetMapping("/restaurant")
    public ResponseEntity<Page<FoodCourtListDto>> listFoodCourts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        Page<FoodCourtListDto> restaurants = foodCourtHandler.listFoodCourts(page, size);

        if (restaurants.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(restaurants);
    }

    @GetMapping("/dish")
    public ResponseEntity<Page<DishListDto>> listDishes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        Page<DishListDto> dishes = dishHandler.listDishes(page, size);

        if (dishes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(dishes);
    }
}
