package com.example.msFoodCourt.domain.spi;

import com.example.msFoodCourt.domain.model.Dish;
import java.util.List;
import java.util.Optional;

public interface IDishPersistencePort {
    void saveDish(Dish dish);
    List<Dish> getAllDish();
    Dish getDish(Long id);
    void updateDish(Dish dish);
    void deleteDish(Long id);
}