package com.project.order.management.data.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table
@NoArgsConstructor
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
