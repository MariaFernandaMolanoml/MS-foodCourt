package com.example.foodcourt.application.handler;

import com.example.foodcourt.application.dto.OrderCreateRequest;
import com.example.foodcourt.application.dto.OrderResponse;
import com.example.foodcourt.application.dto.PagedOrderResponse;
import com.example.foodcourt.application.mapper.OrderMapper;
import com.example.foodcourt.domain.api.IOrderServicePort;
import com.example.foodcourt.domain.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderHandler implements IOrderHandler {

    private final IOrderServicePort orderServicePort;
    private final OrderMapper orderMapper;

    @Override
    public OrderResponse createOrder(OrderCreateRequest request, String clientDocument) {
        Order order = orderMapper.toModel(request);
        order.setClientDocument(clientDocument);
        order.setStatus("PENDING");
        order.setEmployeeDocument("Sin asignar");
        order.setDate(java.time.LocalDate.now());
        Order savedOrder = orderServicePort.createOrder(order);
        return orderMapper.toResponse(savedOrder);
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        List<Order> orders = orderServicePort.getAllOrders();
        return orders.stream()
                .map(orderMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public OrderResponse getOrderById(Long id) {
        Order order = orderServicePort.getOrderById(id);
        return orderMapper.toResponse(order);
    }

    @Override
    public OrderResponse updateOrder(Long id, OrderCreateRequest request) {
        Order orderToUpdate = orderMapper.toModel(request);
        orderToUpdate.setId(id);
        Order updatedOrder = orderServicePort.updateOrder(orderToUpdate);
        return orderMapper.toResponse(updatedOrder);
    }

    @Override
    public void deleteOrder(Long id) {
        orderServicePort.deleteOrder(id);
    }

    public PagedOrderResponse getOrdersByStatus(String restaurantNit, List<String> statuses, int page, int size) {
        List<Order> orders = orderServicePort.getOrdersByRestaurantAndStatus(restaurantNit, statuses, page, size);
        List<OrderResponse> content = orderMapper.toResponseList(orders);

        PagedOrderResponse response = new PagedOrderResponse();
        response.setContent(content);
        response.setPage(page);
        response.setSize(size);
        response.setTotalElements(content.size());
        response.setTotalPages((int) Math.ceil((double) content.size() / size));
        return response;
    }

}