package com.onlinepharmacy.order_service.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemRequest {

    private Long medicineId;

    private Integer quantity;
}
