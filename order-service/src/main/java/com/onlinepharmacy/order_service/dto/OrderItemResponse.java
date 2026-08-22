package com.onlinepharmacy.order_service.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class OrderItemResponse {

    private Long medicineId;
    private String medicineName;
    private BigDecimal price;
    private Integer quantity;
    private BigDecimal subtotal;
}
