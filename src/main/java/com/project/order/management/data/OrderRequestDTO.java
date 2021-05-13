package com.project.order.management.data;

import lombok.Data;

@Data
public class OrderRequestDTO {
    private Long productId;
    private int quantity;
}
