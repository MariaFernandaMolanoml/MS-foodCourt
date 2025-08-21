package com.example.msFoodCourt.application.mapper;

import com.example.msFoodCourt.application.dto.DishResponse;
import com.example.msFoodCourt.domain.model.Dish;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DishResponseMapper {

    DishResponse toResponse(Dish dish);

    List<DishResponse> toResponseList(List<Dish> dishList);
}
