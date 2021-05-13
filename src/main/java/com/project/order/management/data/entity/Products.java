package com.project.order.management.data.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table
@NoArgsConstructor
public class Products {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private String price;
    @OneToMany(mappedBy = "products")
    private Set<OrderDetails> orderDetails;
}