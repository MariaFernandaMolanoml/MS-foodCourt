package com.example.foodcourt.infrastructure.output.jpa.adapter;


import com.example.foodcourt.domain.model.Order;
import com.example.foodcourt.domain.spi.IOrderPersistencePort;
import com.example.foodcourt.infrastructure.output.jpa.entity.OrderEntity;
import com.example.foodcourt.infrastructure.output.jpa.entity.RestaurantEntity;
import com.example.foodcourt.infrastructure.output.jpa.entity.RestaurantOrderEntity;
import com.example.foodcourt.infrastructure.output.jpa.mapper.OrderEntityMapper;
import com.example.foodcourt.infrastructure.output.jpa.repository.IOrderRepository;
import com.example.foodcourt.infrastructure.output.jpa.repository.IRestaurantOrderRepository;
import com.example.foodcourt.infrastructure.output.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public class OrderJpaAdapter implements IOrderPersistencePort {

    private final IOrderRepository orderRepository;
    private final OrderEntityMapper orderEntityMapper;
    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantOrderRepository restaurantOrderRepository;

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

    @Override
    public List<Order> getOrdersByRestaurantAndStatus(String restaurantNit, List<String> statuses, int page, int size) {
        RestaurantEntity restaurant = restaurantRepository.findByNit(restaurantNit)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        Pageable pageable = PageRequest.of(page, size);
        Page<RestaurantOrderEntity> roPage = restaurantOrderRepository
                .findByRestaurantAndOrderStatuses(restaurant.getId(), statuses, pageable);

        Map<Long, Order> ordersMap = new HashMap<>();
        for (RestaurantOrderEntity roEntity : roPage.getContent()) {
            OrderEntity orderEntity = roEntity.getOrder();
            Order order = ordersMap.computeIfAbsent(orderEntity.getId(), id -> orderEntityMapper.toModel(orderEntity));
        }
        return new ArrayList<>(ordersMap.values());
    }

}
