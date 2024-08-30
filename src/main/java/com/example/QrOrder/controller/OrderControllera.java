package com.example.QrOrder.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderControllera {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @PostMapping("/order")
    public void placeOrder() {
        // Gửi thông báo tới kênh "/topic/orders" qua WebSocket
        messagingTemplate.convertAndSend("/topic/orders", "New order received");

    }
}
