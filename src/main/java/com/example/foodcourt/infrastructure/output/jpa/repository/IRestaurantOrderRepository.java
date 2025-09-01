package com.example.foodcourt.infrastructure.output.jpa.repository;

import com.example.foodcourt.infrastructure.output.jpa.entity.RestaurantOrderEntity;
import feign.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRestaurantOrderRepository extends JpaRepository<RestaurantOrderEntity, Long> {

    @Query("SELECT ro FROM RestaurantOrderEntity ro WHERE ro.restaurant.id = :restaurantId AND ro.order.status IN :statuses")
    Page<RestaurantOrderEntity> findByRestaurantAndOrderStatuses(
            @Param("restaurantId") Long restaurantId,
            @Param("statuses") List<String> statuses,
            Pageable pageable
    );
}

