package com.example.foodcourt.application.mapper;

import com.example.foodcourt.application.dto.DishResponse;
import com.example.foodcourt.domain.model.Dish;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DishResponseMapper {

    DishResponse toResponse(Dish dish);

    List<DishResponse> toResponseList(List<Dish> dishList);
}
