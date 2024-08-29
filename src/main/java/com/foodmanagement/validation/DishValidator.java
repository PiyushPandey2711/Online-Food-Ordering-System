package com.foodmanagement.validation;

public class DishValidator {

    public static void validateCuisine(String cuisine) {
        if (cuisine == null || cuisine.isEmpty()) {
            throw new IllegalArgumentException("Cuisine cannot be null or empty");
        }
    }

    public static void validateDishId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid Dish ID");
        }
    }
}
