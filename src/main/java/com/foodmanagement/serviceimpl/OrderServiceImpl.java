package com.foodmanagement.serviceimpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodmanagement.entity.Customer;
import com.foodmanagement.entity.CustomerOrder;
import com.foodmanagement.entity.Dish;
import com.foodmanagement.entity.OrderStatus;
import com.foodmanagement.exception.CustomerNotFoundException;
import com.foodmanagement.exception.DishNotFoundException;
import com.foodmanagement.exception.ResourceNotFoundException;
import com.foodmanagement.repository.CustomerRepository;
import com.foodmanagement.repository.DishRepository;
import com.foodmanagement.repository.OrderRepository;
import com.foodmanagement.service.OrderService;
import com.foodmanagement.validation.OrderValidator;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
    private OrderRepository orderRepository;
	
    @Autowired
    private DishRepository dishRepository;
    
    @Autowired
    private CustomerRepository customerRepository;
    

   
    public CustomerOrder placeOrder(CustomerOrder order) {
        OrderValidator.validateCustomerOrder(order);

        Customer customer = customerRepository.findById(order.getCustomer().getId())
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
            
        // Validate and fetch dishes
        List<Dish> validatedDishes = new ArrayList<>();
        for (Dish orderDish : order.getDishes()) {
            Dish dish = dishRepository.findById(orderDish.getId())
                .orElseThrow(() -> new DishNotFoundException("Dish not found: " + orderDish.getName()));
            validatedDishes.add(dish);
        }

        // Create and save order
        order.setCustomer(customer);
        order.setDishes(validatedDishes);
        order.setTotalAmount(calculateTotalPrice(validatedDishes));
        //**New
        order.setStatus(OrderStatus.PLACED);
        order.setOrderDate(LocalDateTime.now());

        CustomerOrder savedOrder = orderRepository.save(order);

        updateDishAvailability(validatedDishes);
        return savedOrder;
    }

    public List<CustomerOrder> getOrdersByCustomerId(Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    public Optional<CustomerOrder> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public double calculateTotalPrice(List<Dish> dishes) {
        return dishes.stream().mapToDouble(Dish::getPrice).sum();
    }

    private void updateDishAvailability(List<Dish> dishes) {
        for (Dish dish : dishes) {
            if (dish.getAvailability() <= 0) {
                throw new DishNotFoundException("Dish not available: " + dish.getName());
            }
            dish.setAvailability(dish.getAvailability() - 1);
            dishRepository.save(dish);
        }
    }
    public CustomerOrder updateOrderStatus(Long orderId, OrderStatus newStatus) {
        CustomerOrder order = orderRepository.findById(orderId)
            .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        order.setStatus(newStatus);
        return orderRepository.save(order);
    }

}
