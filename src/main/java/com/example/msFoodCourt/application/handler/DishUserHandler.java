package com.example.msFoodCourt.application.handler;

import com.example.msFoodCourt.application.dto.DishListDto;
import com.example.msFoodCourt.application.mapper.DishListMapper;
import com.example.msFoodCourt.domain.api.IDishUserServicePort;
import com.example.msFoodCourt.domain.model.Dish;
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