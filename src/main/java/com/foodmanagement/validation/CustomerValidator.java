package com.foodmanagement.validation;

import com.foodmanagement.entity.Customer;

public class CustomerValidator {

    public static void validateCustomer(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null");
        }
        
    }

    public static void validateEmailAndPassword(String email, String password) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
    }
}
