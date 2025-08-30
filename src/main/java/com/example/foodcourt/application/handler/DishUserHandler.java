package com.example.foodcourt.application.handler;

import com.example.foodcourt.application.dto.DishListDto;
import com.example.foodcourt.application.mapper.DishListMapper;
import com.example.foodcourt.domain.api.IDishUserServicePort;
import com.example.foodcourt.domain.model.Dish;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class DishUserHandler {

    private final IDishUserServicePort dishUserServicePort;
    private final DishListMapper dishListMapper;

    public List<DishListDto> getDishes(Long categoryId, int page, int size) {
        List<Dish> dishes = dishUserServicePort.getDishesByCategory(categoryId, page, size);
        return dishes.stream()
                .map(dishListMapper::toDto)
                .collect(Collectors.toList());
    }
}