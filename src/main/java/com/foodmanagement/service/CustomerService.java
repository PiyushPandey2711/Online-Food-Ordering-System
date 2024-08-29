package com.foodmanagement.service;

import java.util.Optional;

import com.foodmanagement.entity.Customer;


public interface CustomerService {
	Customer loginCustomer(String email, String password);
	Optional<Customer> getCustomerDetails(Long id);
	Customer updateCustomerDetails(Customer customer);
	void deleteCustomer(Long id);
	Customer registerCustomer(Customer customer);

}
