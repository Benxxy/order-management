package com.project.order.management.controller;

import com.project.order.management.data.OrderDTO;
import com.project.order.management.data.OrderRequestDTO;
import com.project.order.management.data.entity.Customer;
import com.project.order.management.data.entity.CustomerOrders;
import com.project.order.management.mappers.OrderMapper;
import com.project.order.management.repository.CustomerRepository;
import com.project.order.management.service.CustomerOrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class CustomerOrdersController {

    private final CustomerOrdersService customerOrdersService;
    private final CustomerRepository customerRepository;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<OrderDTO> createOrder(@RequestBody @Valid OrderRequestDTO orderRequestDTO) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Customer customer = customerRepository.findByUsername(userDetails.getUsername());
        CustomerOrders customerOrders = customerOrdersService.createOrder(orderRequestDTO, customer);
        return new ResponseEntity<OrderDTO>(OrderMapper.INSTANCE.customerOrdersToOrderDTO(customerOrders), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/my",method = RequestMethod.GET)
    public ResponseEntity<List<OrderDTO>> getAllMyOrders() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Customer customer = customerRepository.findByUsername(userDetails.getUsername());
        List<CustomerOrders> customerOrders = customerOrdersService.getOrdersByCustomerId(customer.getId());
        return new ResponseEntity<List<OrderDTO>>(OrderMapper.INSTANCE.customerOrdersListToOrderDTOList(customerOrders), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<OrderDTO>> getAllOrdersByCustomerId(@RequestParam Long customerId) {
        List<CustomerOrders> customerOrders = customerOrdersService.getOrdersByCustomerId(customerId);
        return new ResponseEntity<List<OrderDTO>>(OrderMapper.INSTANCE.customerOrdersListToOrderDTOList(customerOrders), HttpStatus.OK);
    }

}
