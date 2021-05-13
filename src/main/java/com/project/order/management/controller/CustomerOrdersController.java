package com.project.order.management.controller;

import com.project.order.management.data.OrderDTO;
import com.project.order.management.data.OrderRequestDTO;
import com.project.order.management.data.entity.Customer;
import com.project.order.management.data.entity.CustomerOrders;
import com.project.order.management.service.CustomerOrdersService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class CustomerOrdersController {

    private static ModelMapper MAPPER = new ModelMapper();
    private final CustomerOrdersService customerOrdersService;

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody @Valid OrderRequestDTO orderRequestDTO){
        Customer customer = new Customer();
        return new ResponseEntity<OrderDTO>(MAPPER.map(customerOrdersService.createOrder(orderRequestDTO,customer),OrderDTO.class), HttpStatus.CREATED);
    }

    @GetMapping
    @RequestMapping("/my")
    public ResponseEntity<List<OrderDTO>> getAllMyOrders(){
        List<CustomerOrders> customerOrders = customerOrdersService.getMyOrders(1L);
        List<OrderDTO> orderDTOList = customerOrders
                .stream()
                .map(order -> MAPPER.map(order, OrderDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(orderDTOList,HttpStatus.OK);
    }

}
