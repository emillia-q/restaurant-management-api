package com.restaurant.api.mapper;

import com.restaurant.api.dto.response.DishListItemResponse;
import com.restaurant.api.entities.Dish;
import org.springframework.stereotype.Component;

@Component
public class DishMapper {
    public DishListItemResponse toListItemResponse(Dish dish) {
        DishListItemResponse dto = new DishListItemResponse();
        dto.setId(dish.getId());
        dto.setName(dish.getName());
        return dto;
    }
}
