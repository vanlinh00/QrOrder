package com.example.QrOrder.repository;
import com.example.QrOrder.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByStatus(Order.Status status);
    List<Order> findAllByOrderByCreatedAtDesc();

}
