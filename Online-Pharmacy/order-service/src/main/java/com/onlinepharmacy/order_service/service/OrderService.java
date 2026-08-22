package com.onlinepharmacy.order_service.service;

import com.onlinepharmacy.order_service.dto.CreateOrderRequest;
import com.onlinepharmacy.order_service.dto.OrderResponse;
import com.onlinepharmacy.order_service.entity.Order;
import com.onlinepharmacy.order_service.enums.OrderStatus;

import java.util.List;

public interface OrderService {
    OrderResponse createOrder(CreateOrderRequest request);
    OrderResponse getOrderById(Long orderId);

    List<OrderResponse> getOrderByUserId(Long userId);

    OrderResponse updateOrderStatus(Long orderId, OrderStatus status);

    OrderResponse cancelOrder(Long orderId);

}
