package com.restaurant.api.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({
        "id",
        "name"
})

public class DishListItemResponse {
    private Long id;
    private String name;
}
