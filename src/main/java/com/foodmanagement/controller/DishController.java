package com.foodmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodmanagement.entity.Dish;
import com.foodmanagement.service.DishService;

@RestController
@RequestMapping("/api/dishes")
public class DishController {
    @Autowired
    private DishService dishService;
    
    @GetMapping("/cuisine/{cuisine}")
    @PreAuthorize("hasRole('CUSTOMER') or hasRole('ADMIN')")
    public List<Dish> getDishesByCuisine(@PathVariable String cuisine) {
        return dishService.getDishesByCuisine(cuisine);
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('CUSTOMER') or hasRole('ADMIN')")
    public ResponseEntity<Dish> getDishById(@PathVariable Long id) {
        return dishService.getDishById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/sorted-by-rating")
    @PreAuthorize("hasRole('CUSTOMER') or hasRole('ADMIN')")
    public List<Dish> getSortedDishesByRating() {
        return dishService.getSortedDishesByRating();
    }
}

