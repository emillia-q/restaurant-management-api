package com.restaurant.api.service;

import com.restaurant.api.dto.response.RecipeResponse;
import com.restaurant.api.entities.Dish;
import com.restaurant.api.exception.ItemNotFoundException;
import com.restaurant.api.mapper.RecipeMapper;
import com.restaurant.api.repository.DishRepository;
import com.restaurant.api.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final DishRepository dishRepository;
    private final RecipeMapper recipeMapper;

    public List<RecipeResponse> getRecipesByDishId(Long dishId) {
        if(!dishRepository.existsById(dishId))
            throw new ItemNotFoundException(Dish.class,dishId);

        return recipeRepository.findByDishId(dishId).stream()
                .map(recipeMapper::toResponse)
                .toList();
    }
}
