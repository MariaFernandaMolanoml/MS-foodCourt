package com.example.foodcourt.domain.spi;

import com.example.foodcourt.domain.model.Category;
import java.util.List;

public interface ICategoryPersistencePort {
    Category saveCategory(Category category);
    List<Category> getAllCategory();
    List<Category> getCategoryById(Long categoryId);
    void updateCategory(Category category);
    void deleteCategory(Long categoryId);

}