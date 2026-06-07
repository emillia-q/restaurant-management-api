package com.restaurant.api.assembler;

import com.restaurant.api.controller.DishController;
import com.restaurant.api.dto.response.DishDetailResponse;
import com.restaurant.api.dto.response.hateoas.DishResource;
import com.restaurant.api.mapper.DishMapper;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DishModelAssembler extends RepresentationModelAssemblerSupport<DishDetailResponse, DishResource> {

    public DishModelAssembler() {
        super(DishController.class, DishResource.class);
    }

    @Override
    public DishResource toModel(DishDetailResponse entity) {
        DishResource model = new DishResource(
                entity.getId(),
                entity.getName(),
                entity.getCategory(),
                entity.getCalories(),
                entity.getCurrentPrice(),
                entity.getIsAvailable(),
                entity.getIsVegetarian()
        );

        model.add(
                linkTo(methodOn(DishController.class).getRecipesForDish(entity.getId())).withRel("recipes")
        );

        return model;
    }
}
