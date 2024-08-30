package com.example.QrOrder.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDTO {

    @JsonProperty("table_id")
    private Long tableId;

    @JsonProperty("items")
    private List<OrderItemDTO> items;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OrderItemDTO {

        @JsonProperty("menu_item_id")
        private Long menuItemId;

        @JsonProperty("quantity")
        private int quantity;
    }
}
