package com.project.order.management.data;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class OrderRequestDTO {

    @NotEmpty(message = "Please provide productId")
    private Long productId;

    @NotEmpty(message = "Please provide quantity of ordered product")
    private int quantity;
}
