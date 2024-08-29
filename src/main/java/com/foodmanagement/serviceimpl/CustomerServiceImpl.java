package com.foodmanagement.serviceimpl;

import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.foodmanagement.entity.Customer;
import com.foodmanagement.entity.CustomerOrder;

import com.foodmanagement.entity.UserRole;
import com.foodmanagement.exception.CustomerNotFoundException;
import com.foodmanagement.exception.InvalidLoginException;
import com.foodmanagement.repository.CustomerRepository;
import com.foodmanagement.service.CustomerService;
import com.foodmanagement.validation.CustomerValidator;

@Service
public class CustomerServiceImpl implements CustomerService, UserDetailsService{
	
    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    
    public Customer loginCustomer(String email, String password) {
        CustomerValidator.validateEmailAndPassword(email, password);
        Optional<Customer> customer = customerRepository.findByEmail(email);
        if (customer.isPresent()) {
            Customer retrievedCustomer = customer.get();
            if (passwordEncoder.matches(password, retrievedCustomer.getPassword())) {
                return retrievedCustomer;
            }
        }
        throw new InvalidLoginException("Invalid email or password");
    }
    
    
    public Optional<Customer> getCustomerDetails(Long id) {
        return customerRepository.findById(id);
    }
    
   
    public Customer updateCustomerDetails(Customer updatedCustomer) {
        CustomerValidator.validateCustomer(updatedCustomer);
        Optional<Customer> existingCustomerOpt = customerRepository.findById(updatedCustomer.getId());
        if (!existingCustomerOpt.isPresent()) {
            throw new CustomerNotFoundException("Customer not found");
        }

        Customer existingCustomer = existingCustomerOpt.get();

        
        if (updatedCustomer.getName() != null) {
            existingCustomer.setName(updatedCustomer.getName());
        }
        if (updatedCustomer.getEmail() != null) {
            existingCustomer.setEmail(updatedCustomer.getEmail());
        }
        
        if (updatedCustomer.getPassword() != null && !updatedCustomer.getPassword().isEmpty() &&
            !passwordEncoder.matches(updatedCustomer.getPassword(), existingCustomer.getPassword())) {
            // Encode the new password
            existingCustomer.setPassword(passwordEncoder.encode(updatedCustomer.getPassword()));
        }

       
        if (updatedCustomer.getRoles() != null && !updatedCustomer.getRoles().isEmpty()) {
            existingCustomer.setRoles(updatedCustomer.getRoles());
        }

        return customerRepository.save(existingCustomer);
    }
    
    
    
   public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
    


   @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        

        return new org.springframework.security.core.userdetails.User(
                customer.getEmail(),
                customer.getPassword(),
                customer.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                        .collect(Collectors.toList())
        );
    }
    public Customer registerCustomer(Customer customer) {
    	CustomerValidator.validateCustomer(customer);
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customer.getRoles().add(UserRole.CUSTOMER);
        return customerRepository.save(customer);
    }
    
    
}
