package com.foodmanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodmanagement.entity.Customer;
import com.foodmanagement.entity.CustomerOrder;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email);

	
}