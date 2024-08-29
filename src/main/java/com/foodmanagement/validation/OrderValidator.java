package com.foodmanagement.validation;

import com.foodmanagement.entity.CustomerOrder;

public class OrderValidator {

    public static void validateCustomerOrder(CustomerOrder order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        if (order.getCustomer() == null) {
            throw new IllegalArgumentException("Order must have a customer");
        }
        if (order.getDishes() == null || order.getDishes().isEmpty()) {
            throw new IllegalArgumentException("Order must have at least one dish");
        }
    }
}
