package com.example.foodcourt.infrastructure.output.jpa.adapter;


import com.example.foodcourt.domain.model.Order;
import com.example.foodcourt.domain.spi.IOrderPersistencePort;
import com.example.foodcourt.infrastructure.output.jpa.entity.OrderEntity;
import com.example.foodcourt.infrastructure.output.jpa.mapper.OrderEntityMapper;
import com.example.foodcourt.infrastructure.output.jpa.repository.IOrderRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class OrderJpaAdapter implements IOrderPersistencePort {

    private final IOrderRepository orderRepository;
    private final OrderEntityMapper orderEntityMapper;

    @Override
    public Order saveOrder(Order order) {
        OrderEntity entity = orderEntityMapper.toEntity(order);
        OrderEntity saved = orderRepository.save(entity);
        return orderEntityMapper.toModel(saved);
    }

    @Override
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id).map(orderEntityMapper::toModel);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderEntityMapper.toModelList(orderRepository.findAll());
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Order updateOrder(Order order) {
        OrderEntity updated = orderRepository.save(orderEntityMapper.toEntity(order));
        return orderEntityMapper.toModel(updated);
    }

    @Override
    public boolean hasPendingOrders(String clientDocument) {
        return orderRepository.existsByClientDocumentAndStatusIn(
                clientDocument,
                List.of("PENDING", "IN_PROGRESS")
        );
    }

    @Override
    public List<Order> getOrdersByClient(String clientDocument) {
        return orderEntityMapper.toModelList(orderRepository.findByClientDocument(clientDocument));
    }
}
