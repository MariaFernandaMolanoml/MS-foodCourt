package com.example.msFoodCourt.application.mapper;

import com.example.msFoodCourt.application.dto.DishListDto;
import com.example.msFoodCourt.domain.model.Dish;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface DishListMapper {
    @Mapping(target = "category", source = "category.name")
    DishListDto toDto(Dish dish);
    List<DishListDto> toDtoList(List<Dish> dishes);
}
