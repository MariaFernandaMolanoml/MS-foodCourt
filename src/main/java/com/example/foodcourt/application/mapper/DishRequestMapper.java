package com.example.foodcourt.application.mapper;

import com.example.foodcourt.application.dto.DishRequest;
import com.example.foodcourt.domain.model.Category;
import com.example.foodcourt.domain.model.Dish;
import com.example.foodcourt.domain.utils.CategoryResolver;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface DishRequestMapper {

    @Mapping(target = "category", expression = "java(resolveCategory(dishRequest.getCategoryName()))")
    Dish toDish(DishRequest dishRequest);

    default Category resolveCategory(String categoryName) {
        if (categoryName == null) return null;

        Long categoryId = CategoryResolver.getCategoryId(categoryName);
        if (categoryId == null) {
            return null;
        }

        Category category = new Category();
        category.setId(categoryId);
        category.setName(categoryName);
        return category;
    }
}