package com.example.msFoodCourt.domain.api;

import com.example.msFoodCourt.domain.model.Dish;
import com.example.msFoodCourt.domain.model.DishUpdate;

import java.util.List;
import java.util.Optional;

public interface IDishServicePort {
    void saveDish(Dish dish);
    Optional<Dish> getDishById(Long id);
    List<Dish> getAllDish();
    Dish getDish(Long id);
    void updateDish(Dish dish);
    void updateDish(DishUpdate dishUpdate);
    List<Dish> getDishesByRestaurant(Long restaurantId);
    void deleteDish(Long id);

}