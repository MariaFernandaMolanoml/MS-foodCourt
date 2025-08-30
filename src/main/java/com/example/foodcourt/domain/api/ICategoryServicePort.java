package com.example.foodcourt.domain.api;

import com.example.foodcourt.domain.model.Category;
import java.util.List;

public interface ICategoryServicePort {
    Category saveCategory(Category category);
    List<Category> getCategoryById(Long id);
    List<Category> getAllCategory();
    void updateCategory(Category category);
    void deleteCategory(Long id);
}