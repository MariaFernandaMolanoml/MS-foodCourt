package com.example.foodcourt.infrastructure.input.rest;

import com.example.foodcourt.application.dto.OrderCreateRequest;
import com.example.foodcourt.application.dto.OrderResponse;
import com.example.foodcourt.application.handler.OrderHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderRestController {
    private final OrderHandler orderHandler;

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderCreateRequest request) {
        String clientDocument = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        OrderResponse response = orderHandler.createOrder(request, clientDocument);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
