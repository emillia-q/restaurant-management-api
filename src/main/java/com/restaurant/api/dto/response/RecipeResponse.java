package com.restaurant.api.dto.response;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({
        "id",
        "amountRequired",
        "unit",
        "ingredientId",
        "ingredientName"
})
public class RecipeResponse {
    private Long id;
    private Double amountRequired;
    private String unit;
    private Long ingredientId;
    private String ingredientName;
}
