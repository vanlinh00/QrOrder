package com.example.QrOrder.reponses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ListOrderResponse {

    private int countOrder;
    private List<OrderResponse> listOrder;

}
