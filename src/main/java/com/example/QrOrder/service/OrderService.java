package com.example.QrOrder.service;

import com.example.QrOrder.models.Order;
import com.example.QrOrder.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderService implements IOrderService {
    private OrderRepository orderRepository;

    @Override
    public Order createOrder(Order order) throws Exception {
        order.setStatus(Order.Status.RECEIVED);
        return orderRepository.save(order);
    }

    @Override
    public Optional<Order> getOrderById(Long id) throws Exception {

        return orderRepository.findById(id);
    }

    @Override
    public List<Order> getAllOrders() throws Exception {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getOrdersByStatus(Order.Status status) throws Exception {
        return orderRepository.findByStatus(status);
    }

    @Override
    public Order placeOrder(Order order) throws Exception {
        order.setStatus(Order.Status.RECEIVED);
        order.setCreatedAt(new Date());
        order.setUpdatedAt(new Date());
        return orderRepository.save(order);
    }

    @Override
    public Optional<Order> updateOrderStatus(Long orderId, Order.Status status) throws Exception {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            order.setStatus(status);
            orderRepository.save(order);
            return Optional.of(order);
        }
        return Optional.empty();
    }

    @Override
    public void deleteOrder(Long id) throws Exception {

    }
}
