package com.example.msFoodCourt.infrastructure.output.jpa.mapper;

import com.example.msFoodCourt.domain.model.Dish;
import com.example.msFoodCourt.infrastructure.output.jpa.entity.DishEntity;
import org.jetbrains.annotations.NotNull;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IDishEntityMapper {
    DishEntity toEntity(Dish dish);
    Dish toDish(DishEntity entity);
    List<Dish> toDishList(List<DishEntity> entities);

}
