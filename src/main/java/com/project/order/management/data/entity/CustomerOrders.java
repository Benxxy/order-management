package com.project.order.management.data.entity;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table
@NoArgsConstructor
public class CustomerOrders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private String orderNumber;
    private LocalDate orderDate;

    @OneToMany(
            mappedBy = "customerOrders",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<OrderDetails> orderDetails = new ArrayList<OrderDetails>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public List<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public void addOrderDetails(OrderDetails orderDetail) {
        orderDetails.add(orderDetail);
        orderDetail.setCustomerOrders(this);
    }

    public void removeOrderDetails(OrderDetails orderDetail) {
        orderDetails.remove(orderDetail);
        orderDetail.setCustomerOrders(this);
    }
}
