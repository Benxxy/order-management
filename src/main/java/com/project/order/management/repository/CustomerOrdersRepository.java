package com.project.order.management.repository;

import com.project.order.management.data.entity.CustomerOrders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerOrdersRepository extends JpaRepository<CustomerOrders, Long> {
    List<CustomerOrders> findAllByCustomerId(Long customerId);
}
