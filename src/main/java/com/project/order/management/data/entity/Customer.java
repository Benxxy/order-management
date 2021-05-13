package com.project.order.management.data.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;

    @OneToMany(
            mappedBy = "customer",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<CustomerOrders> customerOrders = new ArrayList<CustomerOrders>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<CustomerOrders> getCustomerOrders() {
        return customerOrders;
    }

    public void setCustomerOrders(List<CustomerOrders> customerOrders) {
        this.customerOrders = customerOrders;
    }

    public void addCustomerOrder(CustomerOrders customerOrder) {
        customerOrders.add(customerOrder);
        customerOrder.setCustomer(this);
    }

    public void removeCustomerOrder(CustomerOrders customerOrder) {
        customerOrders.remove(customerOrder);
        customerOrder.setCustomer(this);
    }
}
