package com.example.QrOrder.service;

import com.example.QrOrder.models.Order;

import java.util.List;
import java.util.Optional;

public interface IOrderService {

    // Create a new order
    Order createOrder(Order order) throws Exception;

    // Get order by ID
    Optional<Order> getOrderById(Long id) throws Exception;

    //    // Get all orders
    List<Order> getAllOrders() throws Exception;

    // Get orders by status
    List<Order> getOrdersByStatus(Order.Status status) throws Exception;

    // Create a new order
    Order placeOrder(Order order) throws Exception;

    // Update order status
    Optional<Order> updateOrderStatus(Long orderId, Order.Status status) throws Exception;

    // Delete an order by ID
    void deleteOrder(Long id) throws Exception;

}
