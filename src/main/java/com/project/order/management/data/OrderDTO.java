package com.project.order.management.data;

import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class OrderDTO {
    private Long orderId;
    private String orderNumber;
    private LocalDate orderDate;
    private Set<OrderDetailsDTO> orderDetailsDTO;
}
