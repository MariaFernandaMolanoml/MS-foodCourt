package com.example.foodcourt.domain.spi;

import com.example.foodcourt.domain.model.Dish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IDishPersistencePort {
    void saveDish(Dish dish);
    List<Dish> getAllDish();
    Dish getDish(Long id);
    void updateDish(Dish dish);
    void deleteDish(Long id);
    boolean existById(Long id);
    List<Dish> findByCategory(Long categoryId, int page, int size);
    Page<Dish> findAll(Pageable pageable);
}