package com.example.msFoodCourt.domain.usecase;

import com.example.msFoodCourt.domain.api.IDishUserServicePort;
import com.example.msFoodCourt.domain.model.Dish;
import com.example.msFoodCourt.domain.spi.IDishPersistencePort;

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
