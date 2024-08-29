package com.foodmanagement.entity;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;
import java.util.ArrayList;


@Entity
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String cuisine;
    private double price;
    private int availability;
    private double rating;
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
	public String getCuisine() {
		return cuisine;
	}
	public void setCuisine(String cuisine) {
		this.cuisine = cuisine;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getAvailability() {
		return availability;
	}
	public void setAvailability(int availability) {
		this.availability = availability;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	@Override
	public String toString() {
		return "Dish [id=" + id + ", name=" + name + ", cuisine=" + cuisine + ", price=" + price + ", availability="
				+ availability + ", rating=" + rating + "]";
	}
	public Dish(Long id, String name, String cuisine, double price, int availability, double rating) {
		super();
		this.id = id;
		this.name = name;
		this.cuisine = cuisine;
		this.price = price;
		this.availability = availability;
		this.rating = rating;
	}
	public Dish() {
		super();
		// TODO Auto-generated constructor stub
	}
    // Getters and setters
}













