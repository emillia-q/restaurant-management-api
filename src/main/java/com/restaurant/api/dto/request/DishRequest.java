package com.restaurant.api.dto.request;

import com.restaurant.api.enums.DishCategory;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DishRequest {
    @NotBlank(message = "Dish name is required")
    @Size(min = 2, max = 100, message = "Dish name must be between 2 and 100 characters")
    private String name;

    @NotNull(message = "Category is required")
    private DishCategory category;

    @NotNull(message = "Calories field is required")
    @Min(value = 0, message = "Calories cannot be negative") // Can have 0 kcal (e.g. water)
    private Integer calories;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be greater than 0")
    private Double currentPrice;

    @NotNull(message = "Availability status is required")
    private Boolean isAvailable;

    @NotNull(message = "Vegetarian status is required")
    private Boolean isVegetarian;
}
