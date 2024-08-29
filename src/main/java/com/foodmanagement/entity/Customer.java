package com.foodmanagement.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

@Entity
public class Customer {
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	 	
	    private String name;
	    
	    @Column(unique=true)
	    private String email;
	    
	    private String password;

	    @ElementCollection(fetch = FetchType.EAGER)
	    @Enumerated(EnumType.STRING)
	    private Set<UserRole> roles = new HashSet<>();

	    @JsonManagedReference
	    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<CustomerOrder> orders = new ArrayList<>();

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public Set<UserRole> getRoles() {
			return roles;
		}

		public void setRoles(Set<UserRole> roles) {
			this.roles = roles;
		}

		public List<CustomerOrder> getOrders() {
			return orders;
		}

		public void setOrders(List<CustomerOrder> orders) {
			this.orders = orders;
		}

		@Override
		public String toString() {
			return "Customer [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", roles="
					+ roles + ", orders=" + orders + "]";
		}

		public Customer(Long id, String name, String email, String password, Set<UserRole> roles,
				List<CustomerOrder> orders) {
			super();
			this.id = id;
			this.name = name;
			this.email = email;
			this.password = password;
			this.roles = roles;
			this.orders = orders;
		}

		public Customer() {
			super();
			// TODO Auto-generated constructor stub
		}
		
	    
	    
    // Getters, setters, constructors
}