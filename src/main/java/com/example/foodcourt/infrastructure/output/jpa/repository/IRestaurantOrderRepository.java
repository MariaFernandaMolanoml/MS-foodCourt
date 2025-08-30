package com.example.foodcourt.infrastructure.output.jpa.repository;

import com.example.foodcourt.infrastructure.output.jpa.entity.RestaurantOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRestaurantOrderRepository extends JpaRepository<RestaurantOrderEntity, Long> {

    @Query("SELECT ro FROM RestaurantOrderEntity ro WHERE ro.restaurant.id = :restaurantId AND ro.order.status IN :statuses")
    List<RestaurantOrderEntity> findByRestaurantAndOrderStatuses(Long restaurantId, List<String> statuses);
}

