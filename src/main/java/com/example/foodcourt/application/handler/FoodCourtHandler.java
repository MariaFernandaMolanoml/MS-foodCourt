package com.example.foodcourt.application.handler;

import com.example.foodcourt.application.dto.FoodCourtListDto;
import com.example.foodcourt.application.mapper.FoodCourtMapper;
import com.example.foodcourt.domain.model.Restaurant;
import com.example.foodcourt.infrastructure.output.jpa.adapter.RestaurantJpaAdapter;
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
