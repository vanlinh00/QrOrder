package com.example.QrOrder.reponses;

import com.example.QrOrder.dtos.OrderDTO;
import com.example.QrOrder.models.Order;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {

    private Long tableId;
    private List<OrderResponse.OrderItemResponse> items;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Order.Status status;

    private Timestamp createdAt;
    private Timestamp updatedAt;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OrderItemResponse {

        private Long menuItemId;

        private int quantity;
    }
}
