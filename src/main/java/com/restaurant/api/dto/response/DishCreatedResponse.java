package com.restaurant.api.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.restaurant.api.enums.DishCategory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({
        "id",
        "name",
        "category",
        "calories",
        "currentPrice",
        "isAvailable",
        "isVegetarian"
})
public class DishCreatedResponse {
    private Long id;

    private String name;

    private DishCategory category;

    private Integer calories;

    private Double currentPrice;

    private Boolean isAvailable;

    private Boolean isVegetarian;
}
