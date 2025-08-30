package com.example.foodcourt.infrastructure.output.jpa.mapper;

import com.example.foodcourt.domain.model.Category;
import com.example.foodcourt.infrastructure.output.jpa.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ICategoryEntityMapper {
    CategoryEntity toEntity(Category category);
    Category toCategory(CategoryEntity entity);
    List<Category> toCategoryList(List<CategoryEntity> entities);
}
