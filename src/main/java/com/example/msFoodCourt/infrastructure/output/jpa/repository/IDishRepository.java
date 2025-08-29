package com.example.msFoodCourt.infrastructure.output.jpa.repository;

import com.example.msFoodCourt.infrastructure.output.jpa.entity.DishEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IDishRepository extends JpaRepository<DishEntity, Long> {
    Page<DishEntity> findByCategoryId(Long categoryId, Pageable pageable);
}