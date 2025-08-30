package com.example.foodcourt.infrastructure.output.jpa.mapper;

import com.example.foodcourt.domain.model.Order;
import com.example.foodcourt.infrastructure.output.jpa.entity.OrderDishEntity;
import com.example.foodcourt.infrastructure.output.jpa.entity.OrderEntity;
import com.example.foodcourt.infrastructure.output.jpa.entity.RestaurantOrderEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;


import java.util.List;
@Mapper(componentModel = "spring",
        uses = RestaurantOrderEntityMapper.class,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface OrderEntityMapper {

    Order toModel(OrderEntity entity);

    List<Order> toModelList(List<OrderEntity> entities);

    OrderEntity toEntity(Order model);

    List<OrderEntity> toEntityList(List<Order> models);

    @AfterMapping
    default void linkChildren(@MappingTarget OrderEntity target) {
        if (target.getRestaurantOrders() == null) return;
        for (RestaurantOrderEntity ro : target.getRestaurantOrders()) {
            ro.setOrder(target);
            if (ro.getDishes() != null) {
                for (OrderDishEntity od : ro.getDishes()) {
                    od.setRestaurantOrder(ro);
                }
            }
        }
    }
}




