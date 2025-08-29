package com.example.msFoodCourt.application.mapper;


import com.example.msFoodCourt.application.dto.FoodCourtListDto;
import com.example.msFoodCourt.domain.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface FoodCourtMapper {
    FoodCourtListDto toDto(Restaurant restaurant);
}
