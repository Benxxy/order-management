package com.project.order.management.service;

import com.project.order.management.data.OrderRequestDTO;
import com.project.order.management.data.entity.Customer;
import com.project.order.management.data.entity.CustomerOrders;

import java.util.List;

public interface CustomerOrdersService {
    public CustomerOrders createOrder(OrderRequestDTO orderRequestDTO, Customer customer);
    public List<CustomerOrders> getMyOrders(Long customerId);
}
