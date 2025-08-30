package com.example.foodcourt.application.mapper;


import com.example.foodcourt.application.dto.FoodCourtListDto;
import com.example.foodcourt.domain.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface FoodCourtMapper {
    FoodCourtListDto toDto(Restaurant restaurant);
}
