package com.example.foodcourt.domain.api;

import com.example.foodcourt.domain.model.Dish;
import java.util.List;

public interface IDishUserServicePort {
    List<Dish> getDishesByCategory(Long categoryId, int page, int size);
}
