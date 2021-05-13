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
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$",
            message = "Password must contain:" +
                    "Password must contain at least one digit [0-9].\n" +
                    "Password must contain at least one lowercase Latin character [a-z].\n" +
                    "Password must contain at least one uppercase Latin character [A-Z].\n" +
                    "Password must contain at least one special character like ! @ # & ( ).\n" +
                    "Password must contain a length of at least 8 characters and a maximum of 20 characters. ")
    private String password;
}
