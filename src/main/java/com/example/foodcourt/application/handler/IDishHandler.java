package com.example.foodcourt.application.handler;

import com.example.foodcourt.application.dto.DishListDto;
import com.example.foodcourt.application.dto.DishRequest;
import com.example.foodcourt.application.dto.DishResponse;
import com.example.foodcourt.application.dto.DishUpdateRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IDishHandler {
    void saveDish(DishRequest dishRequest);
    List<DishResponse> getAllDishes();
    DishResponse getDish(Long id);
    void deleteDish(Long id);
    List<DishResponse> getDishesByRestaurant(Long restaurantId);
    void updateDish(Long id, DishUpdateRequest dishUpdateRequest);
    Page<DishListDto> listDishes(int page, int size);
}