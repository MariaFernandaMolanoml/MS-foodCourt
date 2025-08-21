package com.example.msFoodCourt.infrastructure.output.jpa.repository;

import com.example.msFoodCourt.infrastructure.output.jpa.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<CategoryEntity, Long> {
}