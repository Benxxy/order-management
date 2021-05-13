package com.project.order.management.controller;

import com.project.order.management.data.CustomerDTO;
import com.project.order.management.data.JwtResponse;
import com.project.order.management.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<JwtResponse> createCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        JwtResponse jwtResponse = new JwtResponse(customerService.createCustomer(customerDTO));
        return new ResponseEntity<JwtResponse>(jwtResponse, HttpStatus.CREATED);
    }
}
