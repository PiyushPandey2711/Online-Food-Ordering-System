package com.foodmanagement.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodmanagement.entity.Dish;
import com.foodmanagement.exception.DishNotFoundException;
import com.foodmanagement.repository.DishRepository;
import com.foodmanagement.service.DishService;
import com.foodmanagement.validation.DishValidator;


@Service
public class DishServiceImpl implements DishService {
	
	@Autowired
	private DishRepository dishRepository;

	@Override
    public List<Dish> getDishesByCuisine(String cuisine) {
        DishValidator.validateCuisine(cuisine);
        return dishRepository.findByCuisine(cuisine);
    }

    @Override
    public Optional<Dish> getDishById(Long id) {
        DishValidator.validateDishId(id);
        Optional<Dish> dish = dishRepository.findById(id);
        if (dish.isPresent()) {
            return dish;
        } else {
            throw new DishNotFoundException("Dish not found for id: " + id);
        }
    }

    @Override
    public List<Dish> getSortedDishesByRating() {
        return dishRepository.findAllByOrderByRatingDesc();
    }

}
