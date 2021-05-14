package com.project.order.management;

import com.project.order.management.repository.CustomerOrdersRepository;
import com.project.order.management.repository.CustomerRepository;
import com.project.order.management.repository.OrderDetailsRepository;
import com.project.order.management.repository.ProductsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestLoadDataForTestCases {

    @Autowired
    private CustomerOrdersRepository customerOrdersRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    public void testLoadDataForTestCaseForCustomerOrders() {
        assertEquals(5, customerOrdersRepository.findAll().size());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testLoadDataForTestCaseForCustomer() {
        assertEquals(1, customerRepository.findAll().size());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testLoadDataForTestCaseForOrderDetails() {
        assertEquals(5, orderDetailsRepository.findAll().size());
    }

    @Test
    public void testLoadDataForTestCaseForProducts() {
        assertEquals(2, productsRepository.findAll().size());
    }
}
