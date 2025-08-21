package com.example.msFoodCourt.application.mapper;

import com.example.msFoodCourt.application.dto.DishRequest;
import com.example.msFoodCourt.domain.model.Dish;
import com.example.msFoodCourt.domain.utils.CategoryResolver;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface DishRequestMapper {

    @Mapping(target = "categoryId", expression = "java(resolveCategoryId(dishRequest.getCategoryName()))")
    Dish toDish(DishRequest dishRequest);

    default Long resolveCategoryId(String categoryName) {
        if (categoryName == null) return null;
        return CategoryResolver.getCategoryId(categoryName);
    }
}