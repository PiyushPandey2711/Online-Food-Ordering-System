package com.foodmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodmanagement.entity.Dish;

public interface DishRepository extends JpaRepository<Dish, Long> {
    List<Dish> findByCuisine(String cuisine);
    List<Dish> findAllByOrderByRatingDesc();
}
