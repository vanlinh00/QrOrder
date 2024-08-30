package com.example.QrOrder.repository;


import com.example.QrOrder.models.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    void deleteByOrderId(Long id);

    List<OrderItem> findByOrderId(Long orderId);
}
