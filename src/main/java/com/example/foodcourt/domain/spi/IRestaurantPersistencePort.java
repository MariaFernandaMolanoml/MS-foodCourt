package com.example.foodcourt.domain.spi;

import com.example.foodcourt.domain.model.Restaurant;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface IRestaurantPersistencePort {
    Optional<Restaurant> findById(Long id);
    void save(Restaurant restaurant);
    boolean existsByNit(String nit);
    List<Restaurant> findAll();
    Optional<Restaurant> findByNit(String nit);
    Page<Restaurant> findAllOrderedByName(int page, int size);
    Page<Restaurant> findAllWithPagination(int page, int size);
}

