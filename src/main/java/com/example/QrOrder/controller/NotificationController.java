package com.example.QrOrder.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class NotificationController {
    private SimpMessagingTemplate template;
    @Autowired
    public NotificationController(SimpMessagingTemplate template) {
        this.template = template;
    }
    public void sendOrderNotification() {
        this.template.convertAndSend("/topic/orders", "New order placed");
    }
}
