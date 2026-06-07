package com.restaurant.api.dto.response.hateoas;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.restaurant.api.enums.DishCategory;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DishResource extends RepresentationModel<DishResource> {
    private final Long id;

    private final String name;

    private final DishCategory category;

    private final Integer calories;

    private final Double currentPrice;

    private final Boolean isAvailable;

    private final Boolean isVegetarian;
}
