package com.project.order.management.data;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductsDTO {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
}
