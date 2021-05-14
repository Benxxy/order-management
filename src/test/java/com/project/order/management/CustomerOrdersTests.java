package com.project.order.management;

import com.project.order.management.data.OrderRequestDTO;
import com.project.order.management.data.entity.Customer;
import com.project.order.management.data.entity.CustomerOrders;
import com.project.order.management.data.entity.OrderDetails;
import com.project.order.management.data.entity.Products;
import com.project.order.management.repository.CustomerOrdersRepository;
import com.project.order.management.repository.CustomerRepository;
import com.project.order.management.repository.ProductsRepository;
import com.project.order.management.service.CustomerOrdersService;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@ActiveProfiles(value = "test")
@AutoConfigureMockMvc(addFilters = false)
@WithMockUser
public class CustomerOrdersTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerOrdersService customerOrdersService;

    @MockBean
    private CustomerOrdersRepository customerOrdersRepository;

    @MockBean
    private ProductsRepository productsRepository;

    @MockBean
            private CustomerRepository customerRepository;

    Authentication authentication = Mockito.mock(Authentication.class);

    List<CustomerOrders> customerOrders = new ArrayList<>();
    List<OrderDetails> orderDetails = new ArrayList<>();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/d");
    String date = "2021/05/14";

    Customer customer =
            new Customer(1L,
            "TestName",
            "TestLastName",
            "test@email.com",
            "username1",
            "Password29",
            customerOrders);

    CustomerOrders customerOrder =
            new CustomerOrders(1L,
                    customer,
                    "7cc4dfbd-6671-49d8-a54d-994b57af693a",
                    LocalDate.parse(date,formatter),
                    orderDetails);

    Products product =
            new Products(1L,
                    "Iphone 10",
                    "Mobilephone",
                    BigDecimal.valueOf(10),
                    orderDetails);

    OrderDetails orderDetail =
            new OrderDetails(1L,
                    customerOrder,
                    product,
                    2,
                    BigDecimal.valueOf(20));



    @Test
    public void retrieveCustomerOrdersByCustomerIdTest() throws Exception {
        customerOrders.add(customerOrder);
        orderDetails.add(orderDetail);
        Mockito.when(customerOrdersService.getOrdersByCustomerId(1L))
                .thenReturn(customerOrders);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/order?customerId=1").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());
        String expected = "[\n" +
                "    {\n" +
                "        \"orderId\": 1,\n" +
                "        \"orderNumber\": \"7cc4dfbd-6671-49d8-a54d-994b57af693a\",\n" +
                "        \"orderDate\": \"2021-05-14\",\n" +
                "        \"orderDetailsDTO\": [\n" +
                "            {\n" +
                "                \"productName\": \"Iphone 10\",\n" +
                "                \"quantity\": 2,\n" +
                "                \"price\": 10,\n" +
                "                \"totalPrice\": 20\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "]";

        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), JSONCompareMode.LENIENT);
    }

    @Test
    public void addOrderTest() throws Exception {
        customerOrders.add(customerOrder);
        orderDetails.add(orderDetail);
        Mockito.when(customerRepository.findByUsername(Mockito.anyString()))
                .thenReturn(customer);
        Mockito.when(customerOrdersService
                .createOrder(Mockito.any(OrderRequestDTO.class),Mockito.any(Customer.class)))
                .thenReturn(customerOrder);
        Mockito.when(productsRepository.findById(Mockito.anyLong()))
                .thenReturn(java.util.Optional.ofNullable(product));

        UsernamePasswordAuthenticationToken principal =
                new UsernamePasswordAuthenticationToken("username", "Password29");
        Mockito.when(authentication.getPrincipal()).thenReturn(principal);

        JSONObject requestOrderDTO = new JSONObject();
        requestOrderDTO.put("productId",1);
        requestOrderDTO.put("quantity",2);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/order")
                .accept(MediaType.APPLICATION_JSON).content(String.valueOf(requestOrderDTO))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse().getContentAsString());

        String expected =
                "    {\n" +
                "        \"orderId\": 1,\n" +
                "        \"orderNumber\": \"7cc4dfbd-6671-49d8-a54d-994b57af693a\",\n" +
                "        \"orderDate\": \"2021-05-14\",\n" +
                "        \"orderDetailsDTO\": [\n" +
                "            {\n" +
                "                \"productName\": \"Iphone 10\",\n" +
                "                \"quantity\": 2,\n" +
                "                \"price\": 10,\n" +
                "                \"totalPrice\": 20\n" +
                "            }\n" +
                "        ]\n" +
                "    }";

        int status = result.getResponse().getStatus();

        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), JSONCompareMode.LENIENT);
        assertEquals(HttpStatus.CREATED.value(), status);
    }
}
