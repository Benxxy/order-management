package com.project.order.management.data;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderDetailsDTO {
    private String productName;
    private int quantity;
    private BigDecimal price;
    private BigDecimal totalPrice;
}
