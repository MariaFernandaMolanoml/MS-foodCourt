package com.example.foodcourt.application.mapper;

import com.example.foodcourt.application.dto.RestaurantOrderRequest;
import com.example.foodcourt.application.dto.RestaurantOrderResponse;
import com.example.foodcourt.domain.model.RestaurantOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = OrderDishMapper.class,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface RestaurantOrderMapper {

    @Mapping(target = "dishes", source = "dishes")
    RestaurantOrder toModel(RestaurantOrderRequest request);

    @Mapping(target = "dishes", source = "dishes")
    RestaurantOrderResponse toResponse(RestaurantOrder model);

    List<RestaurantOrder> toModelList(List<RestaurantOrderRequest> requests);

    List<RestaurantOrderResponse> toResponseList(List<RestaurantOrder> models);
}

