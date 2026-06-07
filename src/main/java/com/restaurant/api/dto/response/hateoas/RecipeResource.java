package com.restaurant.api.dto.response.hateoas;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RecipeResource extends RepresentationModel<RecipeResource> {
    private final Long id;
    private final Double amountRequired;
    private final String unit;
    private final Long ingredientId;
    private final String ingredientName;
}
