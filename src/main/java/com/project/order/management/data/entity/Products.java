package com.project.order.management.data.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private BigDecimal price;

    @OneToMany(
            mappedBy = "products",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<OrderDetails> orderDetails = new ArrayList<OrderDetails>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public void addOrderDetails(OrderDetails orderDetail) {
        orderDetails.add(orderDetail);
        orderDetail.setProducts(this);
    }

    public void removeOrderDetails(OrderDetails orderDetail) {
        orderDetails.remove(orderDetail);
        orderDetail.setProducts(this);
    }
}
