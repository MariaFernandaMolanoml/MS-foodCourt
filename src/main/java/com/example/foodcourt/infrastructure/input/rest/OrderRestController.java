package com.example.foodcourt.infrastructure.input.rest;

import com.example.foodcourt.application.dto.OrderCreateRequest;
import com.example.foodcourt.application.dto.OrderResponse;
import com.example.foodcourt.application.dto.PagedOrderResponse;
import com.example.foodcourt.application.handler.OrderHandler;
import com.example.foodcourt.domain.api.IRestaurantEmployeeServicePort;
import com.example.foodcourt.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderRestController {
    private final OrderHandler orderHandler;
    private final IRestaurantEmployeeServicePort restaurantEmployeeServicePort;

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderCreateRequest request) {
        String clientDocument = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        OrderResponse response = orderHandler.createOrder(request, clientDocument);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/restaurant/orders")
    public ResponseEntity<PagedOrderResponse> getOrdersByStatus(
            @RequestParam List<String> statuses,
            @RequestParam int page,
            @RequestParam int size) {

        String employeeDocument = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String restaurantNit = restaurantEmployeeServicePort.findRestaurantNitByEmployeeDocument(employeeDocument);

        PagedOrderResponse response = orderHandler.getOrdersByStatus(restaurantNit, statuses, page, size);
        return ResponseEntity.ok(response);
    }
}
