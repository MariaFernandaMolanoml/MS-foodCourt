package com.example.foodcourt.domain.api;

import com.example.foodcourt.domain.model.Dish;
import com.example.foodcourt.domain.model.DishUpdate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IDishServicePort {
    void saveDish(Dish dish, String documentFromToken);
    Optional<Dish> getDishById(Long id);
    List<Dish> getAllDish();
    Dish getDish(Long id);
    void updateDish(DishUpdate dishUpdate, String documentFromToken);
    List<Dish> getDishesByRestaurant(Long restaurantId);
    void deleteDish(Long id);
    Page<Dish> findAll(Pageable pageable);
}