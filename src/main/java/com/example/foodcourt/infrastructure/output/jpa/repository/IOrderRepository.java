package com.example.foodcourt.infrastructure.output.jpa.repository;

import com.example.foodcourt.infrastructure.output.jpa.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findByClientDocumentAndStatusIn(String clientDocument, List<String> statuses);
    List<OrderEntity> findByClientDocument(String clientDocument);
    boolean existsByClientDocumentAndStatusIn(String clientDocument, List<String> statuses);
}

