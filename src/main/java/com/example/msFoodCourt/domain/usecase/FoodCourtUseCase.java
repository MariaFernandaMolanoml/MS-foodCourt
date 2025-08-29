package com.example.msFoodCourt.domain.usecase;

import com.example.msFoodCourt.domain.api.IFoodCourtServicePort;
import com.example.msFoodCourt.domain.model.Restaurant;
import com.example.msFoodCourt.domain.spi.IRestaurantPersistencePort;
import org.springframework.data.domain.Page;

import java.util.List;

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
