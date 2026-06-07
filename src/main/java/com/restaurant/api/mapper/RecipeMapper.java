package com.restaurant.api.mapper;

import com.restaurant.api.dto.response.RecipeResponse;
import com.restaurant.api.entities.Recipe;
import org.springframework.stereotype.Component;

@Component
public class RecipeMapper {
    public RecipeResponse toResponse(Recipe recipe) {
        RecipeResponse dto = new RecipeResponse();
        dto.setId(recipe.getId());
        dto.setAmountRequired(recipe.getAmountRequired());
        dto.setUnit(recipe.getUnit());
        dto.setIngredientId(recipe.getIngredient().getId());
        dto.setIngredientName(recipe.getIngredient().getName());

        return dto;
    }
}
