package com.example.QrOrder.service;

import com.example.QrOrder.dtos.OrderDTO;
import com.example.QrOrder.dtos.StatusDTO;
import com.example.QrOrder.models.Order;
import com.example.QrOrder.models.OrderItem;
import com.example.QrOrder.reponses.ListOrderResponse;
import com.example.QrOrder.reponses.OrderResponse;
import com.example.QrOrder.repository.OrderItemRepository;
import com.example.QrOrder.repository.OrderRepository;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public interface IOrderService {
    void createOrder(Order order, List<OrderItem> orderItems);

    Order getOrderById(Long id);

    void deleteOrder(Long orderId);

    OrderResponse getOrderDtoById(Long id);

    ListOrderResponse getOrdersByStatus(StatusDTO statusDTO);
}
