package com.example.foodcourt.domain.api;

import com.example.foodcourt.domain.model.Order;

import java.util.List;

public interface IOrderServicePort {

    Order createOrder(Order order);
    List<Order> getOrdersByClient(String clientDocument);
    boolean hasPendingOrders(String clientDocument);
    List<Order> getAllOrders();
    Order getOrderById(Long id);
    Order updateOrder(Order order);
    void deleteOrder(Long id);
}
