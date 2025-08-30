package com.example.foodcourt.application.mapper;

import com.example.foodcourt.application.dto.OrderDishRequest;
import com.example.foodcourt.application.dto.OrderDishResponse;
import com.example.foodcourt.domain.model.OrderDish;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface OrderDishMapper {

    OrderDish toModel(OrderDishRequest request);

    OrderDishResponse toResponse(OrderDish model);

    List<OrderDish> toModelList(List<OrderDishRequest> requests);

    List<OrderDishResponse> toResponseList(List<OrderDish> models);
}
