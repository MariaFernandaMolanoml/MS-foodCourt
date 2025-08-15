package com.example.msFoodCourt.domain.spi;

import com.example.msFoodCourt.domain.model.Restaurant;

import java.util.List;
import java.util.Optional;

public interface IRestaurantPersistencePort {

    void save(Restaurant restaurant);

    boolean existsByNit(String nit);

    List<Restaurant> findAll();

    Optional<Restaurant> findById(Long id);

    boolean existsOwnerById(Long idOwner);
}
