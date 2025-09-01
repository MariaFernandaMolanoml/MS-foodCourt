package com.example.foodcourt.application.mapper;

import com.example.foodcourt.application.dto.OrderCreateRequest;
import com.example.foodcourt.application.dto.OrderResponse;
import com.example.foodcourt.domain.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = RestaurantOrderMapper.class,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface OrderMapper {

    @Mapping(target = "restaurantOrders", source = "restaurants")
    Order toModel(OrderCreateRequest request);

    @Mapping(target = "restaurants", source = "restaurantOrders")
    OrderResponse toResponse(Order model);
    List<OrderResponse> toResponseList(List<Order> models);
}
