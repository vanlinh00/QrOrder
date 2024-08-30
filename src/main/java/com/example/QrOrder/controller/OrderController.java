package com.example.QrOrder.controller;

import com.example.QrOrder.dtos.OrderDTO;
import com.example.QrOrder.dtos.StatusDTO;
import com.example.QrOrder.exceptions.ResourceNotFoundException;
import com.example.QrOrder.models.MenuItem;
import com.example.QrOrder.models.Order;

import com.example.QrOrder.models.OrderItem;
import com.example.QrOrder.reponses.ListOrderResponse;
import com.example.QrOrder.reponses.OrderResponse;
import com.example.QrOrder.service.MenuItemService;
import com.example.QrOrder.service.OrderService;
import com.example.QrOrder.service.TableService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/api/orders")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final MenuItemService menuItemService;
    private final TableService tableService;
    private SimpMessagingTemplate messagingTemplate;

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody OrderDTO orderDTO) throws Exception {
        tableService.getTableById(orderDTO.getTableId());
        // Tạo đơn hàng
        Order order = Order.builder()
                .tableId(orderDTO.getTableId())
                .status(Order.Status.RECEIVED)
                .build();
        // Xử lý các món ăn trong đơn hàng
        List<OrderItem> orderItems = orderDTO.getItems().stream().map(itemDTO -> {
            MenuItem menuItem = null;
            try {
                menuItem = menuItemService.getMenuItemById(itemDTO.getMenuItemId());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            OrderItem orderItem = OrderItem.builder()
                    .order(order)
                    .menuItem(menuItem)
                    .quantity(itemDTO.getQuantity())
                    .build();
            return orderItem;
        }).toList();
        orderService.createOrder(order, orderItems);
        return ResponseEntity.ok("Order created successfully");
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> updateOrder(@PathVariable Long id, @RequestBody OrderDTO orderDTO) {
        try {
            Order order = orderService.getOrderById(id);
            Order updatedOrder = Order.builder()
                    .tableId(orderDTO.getTableId())
                    .status(order.getStatus())
                    .build();
            List<OrderItem> updatedOrderItems = orderDTO.getItems().stream().map(itemDTO -> {
                MenuItem menuItem = null;
                try {
                    menuItem = menuItemService.getMenuItemById(itemDTO.getMenuItemId());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                OrderItem orderItem = OrderItem.builder()
                        .menuItem(menuItem)
                        .quantity(itemDTO.getQuantity())
                        .build();
                return orderItem;
            }).toList();
            orderService.updateOrder(id, updatedOrder, updatedOrderItems);
            return ResponseEntity.ok("Order updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error updating order: " + e.getMessage());
        }
    }

    // Endpoint để xóa Order
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        try {
            orderService.deleteOrder(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long id) {
        try {
            OrderResponse orderDTO = orderService.getOrderDtoById(id);

            return ResponseEntity.ok(orderDTO);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<Object> getAllOrder() {
        try {
            ListOrderResponse listOrderResponse = orderService.getAllOrders();
            return ResponseEntity.ok(listOrderResponse);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/status")
    public ListOrderResponse getOrdersByStatus(@RequestBody StatusDTO statusDTO) {
        ListOrderResponse listOrderResponse = orderService.getOrdersByStatus(statusDTO);
        return ResponseEntity.ok(listOrderResponse).getBody();
    }
}
