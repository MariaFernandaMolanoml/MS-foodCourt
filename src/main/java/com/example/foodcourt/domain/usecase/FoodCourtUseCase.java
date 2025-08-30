package com.example.foodcourt.domain.usecase;

import com.example.foodcourt.domain.api.IFoodCourtServicePort;
import com.example.foodcourt.domain.model.Restaurant;
import com.example.foodcourt.domain.spi.IRestaurantPersistencePort;
import org.springframework.data.domain.Page;

public class FoodCourtUseCase implements IFoodCourtServicePort {

    private final IRestaurantPersistencePort restaurantPersistencePort;

    public FoodCourtUseCase(IRestaurantPersistencePort restaurantPersistencePort) {
        this.restaurantPersistencePort = restaurantPersistencePort;
    }

    @Override
    public Page<Restaurant> listFoodCourts(int page, int size) {
        return restaurantPersistencePort.findAllWithPagination(page, size);
    }

    @Override
    public Page<Restaurant> getAllRestaurantsOrderedByName(int page, int size) {
        return restaurantPersistencePort.findAllOrderedByName(page, size);
    }
    }
