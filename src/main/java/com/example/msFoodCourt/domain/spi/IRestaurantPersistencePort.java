package com.example.msFoodCourt.domain.spi;

import com.example.msFoodCourt.domain.model.Restaurant;
import java.util.List;

public interface IRestaurantPersistencePort {

    void save(Restaurant restaurant);

    boolean existsByNit(String nit);

    List<Restaurant> findAll();

}

