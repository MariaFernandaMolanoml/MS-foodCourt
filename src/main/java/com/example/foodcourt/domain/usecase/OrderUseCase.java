package com.example.foodcourt.domain.usecase;

import com.example.foodcourt.domain.api.IOrderServicePort;
import com.example.foodcourt.domain.exception.DishNotFoundException;
import com.example.foodcourt.domain.exception.InvalidDishQuantityException;
import com.example.foodcourt.domain.exception.OrderNotFoundException;
import com.example.foodcourt.domain.exception.PendingOrderException;
import com.example.foodcourt.domain.model.Dish;
import com.example.foodcourt.domain.model.Order;
import com.example.foodcourt.domain.spi.IDishPersistencePort;
import com.example.foodcourt.domain.spi.IOrderPersistencePort;
import java.util.List;
import com.example.foodcourt.domain.model.OrderDish;
import com.example.foodcourt.domain.spi.IRestaurantPersistencePort;

public class OrderUseCase implements IOrderServicePort {

    private final IOrderPersistencePort orderPersistencePort;
    private final IDishPersistencePort dishPersistencePort;
    private final IRestaurantPersistencePort restaurantPersistencePort;

    public OrderUseCase(IOrderPersistencePort orderPersistencePort, IDishPersistencePort dishPersistencePort, IRestaurantPersistencePort restaurantPersistencePort) {
        this.orderPersistencePort = orderPersistencePort;
        this.dishPersistencePort = dishPersistencePort;
        this.restaurantPersistencePort = restaurantPersistencePort;
    }


    @Override
    public Order createOrder(Order order) {
        if (orderPersistencePort.hasPendingOrders(order.getClientDocument())) {
            throw new PendingOrderException();
        }

        order.getRestaurantOrders()
                .forEach(ro -> validateRestaurant(ro.getRestaurantId()));

        order.getRestaurantOrders().forEach(ro ->
                ro.getDishes().forEach(dish -> validateDish(dish, ro.getRestaurantId()))
        );

        return orderPersistencePort.saveOrder(order);
    }

    @Override
    public List<Order> getOrdersByClient(String clientDocument) {
        return orderPersistencePort.getOrdersByClient(clientDocument);
    }

    @Override
    public boolean hasPendingOrders(String clientDocument) {
        return orderPersistencePort.hasPendingOrders(clientDocument);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderPersistencePort.getAllOrders();
    }

    @Override
    public Order getOrderById(Long id) {
        return orderPersistencePort.getOrderById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order with id " + id + " not found"));

    }

    @Override
    public Order updateOrder(Order order) {
        return orderPersistencePort.updateOrder(order);
    }

    @Override
    public void deleteOrder(Long id) {
        orderPersistencePort.deleteOrder(id);
    }

    private void validateDish(OrderDish orderDish, Long restaurantId) {
        if (orderDish.getQuantity() == null || orderDish.getQuantity() <= 0) {
            throw new InvalidDishQuantityException();
        }

        Dish dish = dishPersistencePort.getDish(orderDish.getDishId());
        if (!dish.getRestaurantId().equals(restaurantId)) {
            throw new DishNotFoundException();
        }
        orderDish.setDishName(dish.getName());
    }

    private void validateRestaurant(Long restaurantId) {
        restaurantPersistencePort.findById(restaurantId);
    }

    public List<Order> getOrdersByRestaurantAndStatus(String restaurantNit, List<String> statuses, int page, int size) {
        return orderPersistencePort.getOrdersByRestaurantAndStatus(restaurantNit, statuses, page, size);
    }
}