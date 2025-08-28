package com.example.msFoodCourt.infrastructure.output.jpa.repository;

import com.example.msFoodCourt.infrastructure.output.jpa.entity.RestaurantEmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRestaurantEmployeeRepository extends JpaRepository<RestaurantEmployeeEntity, Long> {
}