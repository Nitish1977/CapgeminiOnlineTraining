package com.onlinepharmacy.order_service.service;

import com.onlinepharmacy.order_service.client.CatalogClient;
import com.onlinepharmacy.order_service.dto.*;
import com.onlinepharmacy.order_service.entity.Order;
import com.onlinepharmacy.order_service.entity.OrderItem;
import com.onlinepharmacy.order_service.enums.OrderStatus;
import com.onlinepharmacy.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CatalogClient catalogClient;

    @Override
    public OrderResponse createOrder(CreateOrderRequest request) {

        Order order = Order.builder()
                .userId(request.getUserId())
                .status(OrderStatus.PENDING)
                .createdAt(LocalDateTime.now())
                .totalAmount(BigDecimal.ZERO)
                .orderItems(new ArrayList<>())
                .build();

        BigDecimal totalAmount = BigDecimal.ZERO;

        for (OrderItemRequest itemRequest : request.getItems()) {

            MedicineResponse medicineResponse =
                    catalogClient.getMedicineById(
                            itemRequest.getMedicineId()
                    );

            // Check stock
            if (medicineResponse.getStockQuantity() < itemRequest.getQuantity()) {
                throw new RuntimeException(
                        "Insufficient stock for medicine: "
                                + medicineResponse.getName()
                );
            }

            // Calculate subtotal
            BigDecimal subtotal =
                    medicineResponse.getPrice()
                            .multiply(
                                    BigDecimal.valueOf(
                                            itemRequest.getQuantity()
                                    )
                            );

            // Create order item
            OrderItem orderItem = OrderItem.builder()
                    .medicineId(medicineResponse.getId())
                    .medicineName(medicineResponse.getName())
                    .price(medicineResponse.getPrice())
                    .quantity(itemRequest.getQuantity())
                    .order(order)
                    .build();

            order.getOrderItems().add(orderItem);

            totalAmount = totalAmount.add(subtotal);
        }

        order.setTotalAmount(totalAmount);

        Order savedOrder = orderRepository.save(order);

        for (OrderItemRequest itemRequest : request.getItems()) {

            catalogClient.reduceStock(
                    itemRequest.getMedicineId(),
                    itemRequest.getQuantity()
            );
        }

        return mapToResponse(savedOrder);
    }

    private OrderResponse mapToResponse(Order order) {

        var items = order.getOrderItems()
                .stream()
                .map(item -> OrderItemResponse.builder()
                        .medicineId(item.getMedicineId())
                        .medicineName(item.getMedicineName())
                        .price(item.getPrice())
                        .quantity(item.getQuantity())
                        .subtotal(
                                item.getPrice()
                                        .multiply(
                                                BigDecimal.valueOf(
                                                        item.getQuantity()
                                                )
                                        )
                        )
                        .build())
                .toList();

        return OrderResponse.builder()
                .orderId(order.getId())
                .userId(order.getUserId())
                .totalAmount(order.getTotalAmount())
                .status(order.getStatus())
                .createdAt(order.getCreatedAt())
                .items(items)
                .build();
    }

    public OrderResponse getOrderById(Long orderId){
        Order order = orderRepository.findById(orderId)
                .orElseThrow(()-> new RuntimeException("Order Not Found with id: "+orderId));

        return mapToResponse(order);
    }



    public List<OrderResponse> getOrderByUserId(Long userId){
        return orderRepository.findByUserId(userId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }


    @Override
    public OrderResponse updateOrderStatus(
            Long orderId,
            OrderStatus status) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Order Not Found with id: " + orderId
                        )
                );

        if (order.getStatus() == OrderStatus.CANCELLED) {
            throw new RuntimeException(
                    "Cancelled order status cannot be changed"
            );
        }

        if (order.getStatus() == OrderStatus.DELIVERED) {
            throw new RuntimeException(
                    "Delivered order status cannot be changed"
            );
        }

        order.setStatus(status);

        Order updatedOrder = orderRepository.save(order);

        return mapToResponse(updatedOrder);
    }


    @Override
    public OrderResponse cancelOrder(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Order Not Found with id: " + orderId
                        )
                );

        // IMPORTANT: Don't restore stock twice
        if (order.getStatus() == OrderStatus.CANCELLED) {
            throw new RuntimeException("Order is already cancelled");
        }

        if (order.getStatus() == OrderStatus.DELIVERED) {
            throw new RuntimeException(
                    "Delivered order cannot be cancelled"
            );
        }

        // Restore stock only once
        for (OrderItem item : order.getOrderItems()) {

            catalogClient.restoreStock(
                    item.getMedicineId(),
                    item.getQuantity()
            );
        }

        order.setStatus(OrderStatus.CANCELLED);

        Order cancelledOrder = orderRepository.save(order);

        return mapToResponse(cancelledOrder);
    }






}