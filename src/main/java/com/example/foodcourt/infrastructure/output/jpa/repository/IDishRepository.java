package com.example.foodcourt.infrastructure.output.jpa.repository;

import com.example.foodcourt.infrastructure.output.jpa.entity.DishEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDishRepository extends JpaRepository<DishEntity, Long> {
    Page<DishEntity> findByCategoryId(Long categoryId, Pageable pageable);
}