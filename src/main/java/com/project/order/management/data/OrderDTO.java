package com.project.order.management.data;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderDTO {
    private String orderId;
    private String orderNumber;
    private Date date;
    private int quantity;
    private BigDecimal price;
    private BigDecimal total;
}
