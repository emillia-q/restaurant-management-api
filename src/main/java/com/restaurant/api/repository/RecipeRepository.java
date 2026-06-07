package com.restaurant.api.repository;

import com.restaurant.api.entities.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findByDishId(Long dishId);
}
