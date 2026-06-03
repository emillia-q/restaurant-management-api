package com.restaurant.api.dto.request;

import com.restaurant.api.enums.DishCategory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DishRequest {
    private String name;

    private DishCategory category;

    private Integer calories;

    private Double currentPrice;

    private Boolean isAvailable;

    private Boolean isVegetarian;
}
