package com.project.order.management.data;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class OrderRequestDTO {

    @NotNull(message = "Please provide productId")
    private Long productId;

    @NotNull(message = "Please provide quantity of ordered product")
    private int quantity;
}
