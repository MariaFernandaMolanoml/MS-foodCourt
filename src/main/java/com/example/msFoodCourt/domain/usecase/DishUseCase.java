package com.example.msFoodCourt.domain.usecase;

import com.example.msFoodCourt.domain.api.IDishServicePort;
import com.example.msFoodCourt.domain.exception.*;
import com.example.msFoodCourt.domain.model.Dish;
import com.example.msFoodCourt.domain.spi.IDishPersistencePort;

import java.util.List;
import java.util.Optional;

public class DishUseCase implements IDishServicePort {

    private final IDishPersistencePort dishPersistencePort;

    public DishUseCase(IDishPersistencePort dishPersistencePort) {
        this.dishPersistencePort = dishPersistencePort;
    }

    @Override
    public void saveDish(Dish dish) {
        validateDish(dish);
        if (dish.getActive() == null) {
            dish.setActive(true);
        }
        dishPersistencePort.saveDish(dish);
    }


    @Override
    public Optional<Dish> getDishById(Long id) {
        return Optional.ofNullable(dishPersistencePort.getDish(id));
    }

    @Override
    public List<Dish> getAllDish() {
        return dishPersistencePort.getAllDish();
    }

    @Override
    public Dish getDish(Long id) {
        return dishPersistencePort.getDish(id);
    }

    @Override
    public void updateDish(Dish dish) {
        if (dish.getName() == null || dish.getName().isBlank()) {
            throw new DishNameNotFoundException();
        }
        if (dish.getDescription() == null || dish.getDescription().isBlank()) {
            throw new DishDescriptionNotFoundException();
        }
        if (dish.getPrice() == null || dish.getPrice() <= 0) {
            throw new DishPriceNotValidException();
        }
        if (dish.getRestaurantId() == null) {
            throw new DishRestaurantNotFoundException();
        }
        if (dish.getCategoryId() == null) {
            throw new DishCategoryNotFoundException();
        }

        dishPersistencePort.updateDish(dish);
    }

    @Override
    public void deleteDish(Long id) {
        dishPersistencePort.deleteDish(id);
    }

    private void validateDish(Dish dish) {
        if (dish.getName() == null || dish.getName().isBlank()) {
            throw new DishNameNotFoundException();
        }
        if (dish.getDescription() == null || dish.getDescription().isBlank()) {
            throw new DishDescriptionNotFoundException();
        }
        if (dish.getPrice() == null || dish.getPrice() <= 0) {
            throw new DishPriceNotValidException();
        }
        if (dish.getRestaurantId() == null) {
            throw new DishRestaurantNotFoundException();
        }
        if (dish.getCategoryId() == null) {
            throw new DishCategoryNotFoundException();
        }
    }
}
