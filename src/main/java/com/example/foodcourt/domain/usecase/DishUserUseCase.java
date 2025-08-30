package com.example.foodcourt.domain.usecase;

import com.example.foodcourt.domain.api.IDishUserServicePort;
import com.example.foodcourt.domain.model.Dish;
import com.example.foodcourt.domain.spi.IDishPersistencePort;

import java.util.List;

public class DishUserUseCase implements IDishUserServicePort {

    private final IDishPersistencePort dishPersistencePort;

    public DishUserUseCase(IDishPersistencePort dishPersistencePort) {
        this.dishPersistencePort = dishPersistencePort;
    }

    @Override
    public List<Dish> getDishesByCategory(Long categoryId, int page, int size) {
        return dishPersistencePort.findByCategory(categoryId, page, size);
    }
}
