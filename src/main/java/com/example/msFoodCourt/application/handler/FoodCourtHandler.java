package com.example.msFoodCourt.application.handler;

import com.example.msFoodCourt.application.dto.FoodCourtListDto;
import com.example.msFoodCourt.application.mapper.FoodCourtMapper;
import com.example.msFoodCourt.domain.model.Restaurant;
import com.example.msFoodCourt.infrastructure.output.jpa.adapter.RestaurantJpaAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FoodCourtHandler {

    private final RestaurantJpaAdapter restaurantJpaAdapter;
    private final FoodCourtMapper foodCourtMapper;

    public Page<FoodCourtListDto> listFoodCourts(int page, int size) {
        Page<Restaurant> restaurants = restaurantJpaAdapter.findAllOrderedByName(page, size);
        return restaurants.map(foodCourtMapper::toDto);
    }
}
