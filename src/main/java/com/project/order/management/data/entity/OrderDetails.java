package com.project.order.management.data.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table
public class OrderDetails {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "customer_orders_id")
    private CustomerOrders customerOrders;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products products;
}
