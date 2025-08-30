package com.example.foodcourt.infrastructure.output.jpa.mapper;

import com.example.foodcourt.domain.model.OrderDish;
import com.example.foodcourt.infrastructure.output.jpa.entity.OrderDishEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderDishEntityMapper {

    OrderDishEntity toEntity(OrderDish model);

    OrderDish toModel(OrderDishEntity entity);

    List<OrderDish> toModelList(List<OrderDishEntity> entities);
    List<OrderDishEntity> toEntityList(List<OrderDish> models);
}




