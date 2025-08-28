package com.example.msFoodCourt.infrastructure.output.jpa.repository;

import com.example.msFoodCourt.infrastructure.output.jpa.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRestaurantRepository extends JpaRepository<RestaurantEntity, Long> {
    boolean existsByNit(String nit);
    Optional<RestaurantEntity> findByNit(String nit);
}
