package com.project.order.management.data;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
public class JwtRequest {

    @NotEmpty(message = "Please provide username")
    private String username;

    @NotEmpty(message = "Please provide password")
    private String password;
}
