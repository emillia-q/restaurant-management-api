package com.restaurant.api.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.restaurant.api.enums.DishCategory;
import com.restaurant.api.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({
        "id",
        "name",
        "category",
        "isAvailable"
})
public class DishListItemResponse {
    private Long id;
    private String name;
    private DishCategory category;
    private Boolean isAvailable;
}
