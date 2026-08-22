package com.onlinepharmacy.order_service.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class MedicineResponse {
    private Long id;
    private String name;
    private String brand;
    private String category;
    private String description;
    private BigDecimal price;
    private Integer stockQuantity;
}
