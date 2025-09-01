package com.example.foodcourt.domain.spi;

import com.example.foodcourt.domain.model.Order;

import java.util.List;
import java.util.Optional;

public interface IOrderPersistencePort {

    Order saveOrder(Order order);
    Optional<Order> getOrderById(Long id);
    List<Order> getAllOrders();
    void deleteOrder(Long id);
    Order updateOrder(Order order);
    boolean hasPendingOrders(String clientDocument);
    List<Order> getOrdersByClient(String clientDocument);
    List<Order> getOrdersByRestaurantAndStatus(String restaurantNit, List<String> statuses, int page, int size);
}