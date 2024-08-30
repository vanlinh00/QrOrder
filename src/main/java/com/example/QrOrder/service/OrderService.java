package com.example.QrOrder.service;

import com.example.QrOrder.dtos.OrderDTO;
import com.example.QrOrder.dtos.StatusDTO;
import com.example.QrOrder.exceptions.ResourceNotFoundException;
import com.example.QrOrder.models.MenuItem;
import com.example.QrOrder.models.Order;
import com.example.QrOrder.models.OrderItem;
import com.example.QrOrder.reponses.ListOrderResponse;
import com.example.QrOrder.reponses.OrderResponse;
import com.example.QrOrder.repository.MenuItemRepository;
import com.example.QrOrder.repository.OrderItemRepository;
import com.example.QrOrder.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService implements IOrderService {

    final OrderRepository orderRepository;
    final OrderItemRepository orderItemRepository;
    final MenuItemRepository menuItemRepository;


    @Override
    @Transactional
    public void createOrder(Order order, List<OrderItem> orderItems) {
        Order newOrder = Order.builder()
                .tableId(order.getTableId())
                .status(order.getStatus())
                .build();
        orderRepository.save(newOrder);
        for (OrderItem item : orderItems) {
            //  item.setOrder(order);
            MenuItem menuItem = menuItemRepository.findById(item.getMenuItem().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("MenuItem not found"));

            OrderItem newItem = OrderItem.builder()
                    .order(newOrder)
                    .menuItem(menuItem)  // Set the MenuItem here
                    .quantity(item.getQuantity())
                    .build();
            orderItemRepository.save(newItem);


        }
    }

    @Transactional
    public void updateOrder(Long orderId, Order updatedOrder, List<OrderItem> updatedOrderItems) {

        // Kiểm tra và lấy Order từ database
        Order existingOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        // Cập nhật thông tin Order
        existingOrder.setTableId(updatedOrder.getTableId());
        existingOrder.setStatus(updatedOrder.getStatus());
        orderRepository.save(existingOrder);

        // Xử lý cập nhật OrderItems
        orderItemRepository.deleteByOrderId(orderId); // Xóa OrderItems cũ trước
        for (OrderItem item : updatedOrderItems) {
            MenuItem menuItem = menuItemRepository.findById(item.getMenuItem().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("MenuItem not found"));

            OrderItem newItem = OrderItem.builder()
                    .order(existingOrder)
                    .menuItem(menuItem)
                    .quantity(item.getQuantity())
                    .build();
            orderItemRepository.save(newItem);
        }
    }

    @Override
    @Transactional
    public void deleteOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found for id: " + orderId));
        orderItemRepository.deleteByOrderId(orderId);
        orderRepository.delete(order);
    }

    @Override
    public OrderResponse getOrderDtoById(Long id) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (orderOptional.isEmpty()) {
            throw new ResourceNotFoundException("Order not found for id: ");
        }
        if (orderOptional.isPresent()) {

            Order order = orderOptional.get();
            List<OrderItem> listOrderItem = orderItemRepository.findByOrderId(order.getId());
            List<OrderResponse.OrderItemResponse> orderItemDTOList = new ArrayList<>();
            for (int i = 0; i < listOrderItem.size(); i++) {
                OrderResponse.OrderItemResponse orderItemDTO = new OrderResponse.OrderItemResponse();
                orderItemDTO.setMenuItemId(listOrderItem.get(i).getMenuItem().getId());
                orderItemDTO.setQuantity(listOrderItem.get(i).getQuantity());
                orderItemDTOList.add(orderItemDTO);
            }

            return OrderResponse.builder()
                    .tableId(order.getTableId())
                    .updatedAt(order.getUpdatedAt())
                    .createdAt(order.getCreatedAt())
                    .status(order.getStatus())
                    .items(orderItemDTOList)
                    .build();

        } else {
            throw new ResourceNotFoundException("Order not found with id " + id);
        }
    }

    @Override
    public Order getOrderById(Long id) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        Order newOrder = orderOptional.get();
        return newOrder;
    }

    public ListOrderResponse getAllOrders() {
        List<OrderResponse> orderResponses = new ArrayList<>();
        List<Order> orders = orderRepository.findAllByOrderByCreatedAtDesc();
        for (int i = 0; i < orders.size(); i++) {
            OrderResponse orderResponse = getOrderDtoById(orders.get(i).getId());
            orderResponses.add(orderResponse);
        }
        return ListOrderResponse
                .builder()
                .listOrder(orderResponses)
                .countOrder(orderResponses.size())
                .build();
    }

    @Override
    public ListOrderResponse getOrdersByStatus(StatusDTO statusDTO) {
        Order.Status status = Order.fromDTO(statusDTO);
        List<Order> listOrder = orderRepository.findByStatus(status);
        List<OrderResponse> orderResponses = new ArrayList<>();
        for (int i = 0; i < listOrder.size(); i++) {
            OrderResponse orderResponse = getOrderDtoById(listOrder.get(i).getId());
            orderResponses.add(orderResponse);
        }
        return ListOrderResponse
                .builder()
                .listOrder(orderResponses)
                .countOrder(orderResponses.size())
                .build();
    }
}
