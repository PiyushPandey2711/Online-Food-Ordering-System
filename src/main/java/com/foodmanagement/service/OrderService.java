package com.foodmanagement.service;

import java.util.List;
import java.util.Optional;

import com.foodmanagement.entity.CustomerOrder;
import com.foodmanagement.entity.Dish;
import com.foodmanagement.entity.OrderStatus;

public interface OrderService {
	//CustomerOrder placeOrder(CustomerOrder order);
	CustomerOrder placeOrder(CustomerOrder order);
	List<CustomerOrder> getOrdersByCustomerId(Long customerId);
	//List<CustomerOrder> getOrdersForCustomer(Long customerId);
	Optional<CustomerOrder> getOrderById(Long id);
	//Optional<CustomerOrder> getOrderDetails(Long orderId);
	double calculateTotalPrice(List<Dish> dishes);
	//String getBillWithCustomerName(Long orderId);
	
	CustomerOrder updateOrderStatus(Long orderId, OrderStatus status);
}
