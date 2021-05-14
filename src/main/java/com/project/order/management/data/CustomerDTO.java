package com.project.order.management.data;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class CustomerDTO {
    private String firstName;
    private String lastName;

    @NotEmpty(message = "Please provide an email")
    @Pattern(regexp = "^(.+)@(.+)$",message = "Email is incorrect!")
    private String email;

    @NotEmpty(message = "Please provide username")
    private String username;

    @NotEmpty(message = "Please provide a password")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",
            message = "Minimum eight characters, at least one letter and one number!")
    private String password;
}
