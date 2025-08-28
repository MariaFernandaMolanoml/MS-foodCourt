package com.example.msFoodCourt.application.mapper;

import com.example.msFoodCourt.application.dto.RestaurantEmployeeRequestDto;
import com.example.msFoodCourt.domain.model.RestaurantEmployee;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IRestaurantEmployeeRequestMapper {
    RestaurantEmployee toRestaurantEmployee(RestaurantEmployeeRequestDto restaurantEmployeeRequestDto);
}
