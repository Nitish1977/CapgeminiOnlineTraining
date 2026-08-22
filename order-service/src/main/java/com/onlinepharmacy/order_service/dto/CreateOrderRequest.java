package com.onlinepharmacy.order_service.dto;

import com.onlinepharmacy.order_service.entity.Order;
import com.onlinepharmacy.order_service.entity.OrderItem;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateOrderRequest {

    private Long userId;

    private List<OrderItemRequest> items;
}
