package com.example.msFoodCourt.domain.api;

import com.example.msFoodCourt.domain.model.Dish;
import java.util.List;

public interface IDishUserServicePort {
    List<Dish> getDishesByCategory(Long categoryId, int page, int size);
}
