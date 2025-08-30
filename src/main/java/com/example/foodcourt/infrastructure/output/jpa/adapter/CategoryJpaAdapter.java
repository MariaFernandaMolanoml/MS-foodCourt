package com.example.foodcourt.infrastructure.output.jpa.adapter;

import com.example.foodcourt.domain.model.Category;
import com.example.foodcourt.domain.spi.ICategoryPersistencePort;
import com.example.foodcourt.infrastructure.output.jpa.entity.CategoryEntity;
import com.example.foodcourt.infrastructure.output.jpa.mapper.ICategoryEntityMapper;
import com.example.foodcourt.infrastructure.output.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;


@RequiredArgsConstructor
public class CategoryJpaAdapter implements ICategoryPersistencePort {

    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;

    @Override
    public Category saveCategory(Category category) {
        CategoryEntity entity = categoryEntityMapper.toEntity(category);
        return categoryEntityMapper.toCategory(categoryRepository.save(entity));
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryEntityMapper.toCategoryList(categoryRepository.findAll());
    }

    @Override
    public List<Category> getCategoryById(Long categoryId) {
        return categoryRepository.findAll().stream()
                .filter(c -> c.getId().equals(categoryId))
                .map(categoryEntityMapper::toCategory)
                .toList();
    }

    @Override
    public void updateCategory(Category category) {
        categoryRepository.save(categoryEntityMapper.toEntity(category));
    }

    @Override
    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}
