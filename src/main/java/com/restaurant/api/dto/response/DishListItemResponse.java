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
        "dishCategory"
})

public class DishListItemResponse {
    private Long id;
    private String name;
    private DishCategory category;
}
