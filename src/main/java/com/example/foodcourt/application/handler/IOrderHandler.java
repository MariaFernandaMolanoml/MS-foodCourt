package com.example.foodcourt.application.handler;

import com.example.foodcourt.application.dto.OrderCreateRequest;
import com.example.foodcourt.application.dto.OrderResponse;

import java.util.List;

public interface IOrderHandler {

    OrderResponse createOrder(OrderCreateRequest request, String clientDocument);
    List<OrderResponse> getAllOrders();
    OrderResponse getOrderById(Long id);
    OrderResponse updateOrder(Long id, OrderCreateRequest request);
    void deleteOrder(Long id);
}
