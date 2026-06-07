package com.restaurant.api.assembler;

import com.restaurant.api.controller.DishController;
import com.restaurant.api.dto.response.RecipeResponse;
import com.restaurant.api.dto.response.hateoas.RecipeResource;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class RecipeModelAssembler extends RepresentationModelAssemblerSupport<RecipeResponse, RecipeResource> {

    public RecipeModelAssembler() {
        super(DishController.class, RecipeResource.class);
    }

    @Override
    public RecipeResource toModel(RecipeResponse entity) {
        RecipeResource model = new RecipeResource(
                entity.getId(),
                entity.getAmountRequired(),
                entity.getUnit(),
                entity.getIngredientId(),
                entity.getIngredientName()
        );

        return model;
    }
}
