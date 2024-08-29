package com.foodmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodmanagement.entity.CustomerOrder;


public interface OrderRepository extends JpaRepository<CustomerOrder, Long> {
    List<CustomerOrder> findByCustomerId(Long customerId);
}
