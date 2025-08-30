package com.example.foodcourt.application.mapper;

import com.example.foodcourt.application.dto.RestaurantEmployeeRequestDto;
import com.example.foodcourt.domain.model.RestaurantEmployee;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IRestaurantEmployeeRequestMapper {
    RestaurantEmployee toRestaurantEmployee(RestaurantEmployeeRequestDto restaurantEmployeeRequestDto);
}
