package com.example.msFoodCourt.application.mapper;

import com.example.msFoodCourt.application.dto.RestaurantRequestDto;
import com.example.msFoodCourt.application.dto.RestaurantResponseDto;
import com.example.msFoodCourt.domain.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface RestaurantRequestMapper {

    Restaurant toRestaurant(RestaurantRequestDto dto);

    RestaurantResponseDto toResponse(Restaurant restaurant);

    List<RestaurantResponseDto> toResponseList(List<Restaurant> restaurants);
}
