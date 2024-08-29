package com.foodmanagement.service;

import java.util.List;
import java.util.Optional;

import com.foodmanagement.entity.Dish;

public interface DishService {
	List<Dish> getDishesByCuisine(String cuisine);
	Optional<Dish> getDishById(Long id);
	List<Dish> getSortedDishesByRating();

}
