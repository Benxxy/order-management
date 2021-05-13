package com.project.order.management.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table
public class CustomerOrders {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private String orderNumber;
    private Date orderDate;

    @OneToMany(mappedBy = "customerOrders")
    private Set<OrderDetails> orderDetails;
}
