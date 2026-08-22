package com.onlinepharmacy.order_service.entity;


import com.onlinepharmacy.order_service.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private BigDecimal totalAmount;

    private OrderStatus status;

    private LocalDateTime createdAt;



    @OneToMany(
            mappedBy = "order",
    cascade =  CascadeType.ALL,
    orphanRemoval = true
    )
    @Builder.Default

    private List<OrderItem> orderItems = new ArrayList<>();
}
