package com.project.order.management.service;

import com.project.order.management.data.OrderRequestDTO;
import com.project.order.management.data.entity.Customer;
import com.project.order.management.data.entity.CustomerOrders;
import com.project.order.management.data.entity.OrderDetails;
import com.project.order.management.data.entity.Products;
import com.project.order.management.repository.CustomerOrdersRepository;
import com.project.order.management.repository.CustomerRepository;
import com.project.order.management.repository.OrderDetailsRepository;
import com.project.order.management.repository.ProductsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerOrdersServiceImpl implements CustomerOrdersService {

    private final CustomerOrdersRepository customerOrdersRepository;
    private final ProductsRepository productsRepository;
    private final OrderDetailsRepository orderDetailsRepository;
    private final CustomerRepository customerRepository;

    @Override
    public CustomerOrders createOrder(OrderRequestDTO orderRequestDTO, Customer customer) {
        Optional<Products> products = productsRepository.findById(orderRequestDTO.getProductId());
        if (!products.isPresent()) {
            throw new EntityNotFoundException("Product with id: " + orderRequestDTO.getProductId() + " does not exists!");
        }
        CustomerOrders customerOrder = new CustomerOrders();
        UUID uuid = UUID.randomUUID();
        customerOrder.setOrderNumber(uuid.toString());
        customerOrder.setCustomer(customer);
        customerOrder.setOrderDate(LocalDate.now());

        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setProducts(products.get());
        orderDetails.setQuantity(orderRequestDTO.getQuantity());
        orderDetails.setTotalPrice(products.get().getPrice().multiply(BigDecimal.valueOf(orderRequestDTO.getQuantity())));

        customerOrder.getOrderDetails().add(orderDetails);
        orderDetails.setCustomerOrders(customerOrder);

        return customerOrdersRepository.save(customerOrder);
    }

    @Override
    public List<CustomerOrders> getOrdersByCustomerId(Long customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (!customer.isPresent()) {
            throw new EntityNotFoundException("Customer with id: " + customerId + " does not exists!");
        }
        return customerOrdersRepository.findAllByCustomerId(customerId);
    }
}
