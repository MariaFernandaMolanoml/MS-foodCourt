package com.example.foodcourt.infrastructure.output.jpa.mapper;

import com.example.foodcourt.domain.model.RestaurantOrder;
import com.example.foodcourt.infrastructure.output.jpa.entity.RestaurantEntity;
import com.example.foodcourt.infrastructure.output.jpa.entity.RestaurantOrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = IOrderDishEntityMapper.class,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface RestaurantOrderEntityMapper {

    // model -> entity
    @Mapping(target = "order", ignore = true) // se setea en el padre
    @Mapping(target = "restaurant", expression = "java(toRestaurantEntity(model.getRestaurantId()))")
    RestaurantOrderEntity toEntity(RestaurantOrder model);

    // entity -> model
    @Mapping(target = "restaurantId", source = "restaurant.id")
    RestaurantOrder toModel(RestaurantOrderEntity entity);

    List<RestaurantOrder> toModelList(List<RestaurantOrderEntity> entities);
    List<RestaurantOrderEntity> toEntityList(List<RestaurantOrder> models);

    // helper
    default RestaurantEntity toRestaurantEntity(Long id) {
        if (id == null) return null;
        RestaurantEntity r = new RestaurantEntity();
        r.setId(id);
        return r;
    }
}




