package com.example.msFoodCourt.domain.spi;

import com.example.msFoodCourt.domain.model.Category;
import java.util.List;
import java.util.Optional;

public interface ICategoryPersistencePort {
    Category saveCategory(Category category);
    List<Category> getAllCategory();
    List<Category> getCategoryById(Long categoryId);
    void updateCategory(Category category);
    void deleteCategory(Long categoryId);

}