package com.example.msFoodCourt.application.handler;

import com.example.msFoodCourt.application.dto.DishRequest;
import com.example.msFoodCourt.application.dto.DishResponse;
import com.example.msFoodCourt.application.dto.DishUpdateRequest;
import jakarta.validation.Valid;

import java.util.List;

public interface IDishHandler {
    void saveDish(DishRequest dishRequest);
    List<DishResponse> getAllDishes();
    DishResponse getDish(Long id);
    void updateDish(DishRequest dishRequest);
    void deleteDish(Long id);
    List<DishResponse> getDishesByRestaurant(Long restaurantId);
    void updateDish(Long id, DishUpdateRequest dishUpdateRequest);
}