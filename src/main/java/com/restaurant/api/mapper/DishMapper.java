package com.restaurant.api.mapper;

import com.restaurant.api.dto.request.DishRequest;
import com.restaurant.api.dto.response.DishDetailResponse;
import com.restaurant.api.dto.response.DishListItemResponse;
import com.restaurant.api.entities.Dish;
import org.springframework.stereotype.Component;

@Component
public class DishMapper {
    public DishListItemResponse toListItemResponse(Dish dish) {
        DishListItemResponse dto = new DishListItemResponse();
        dto.setId(dish.getId());
        dto.setName(dish.getName());
        dto.setCategory(dish.getCategory());
        return dto;
    }

    public DishListItemResponse toListFilterByCategory(Dish dish) {
        DishListItemResponse dto = new DishListItemResponse();
        dto.setId(dish.getId());
        dto.setName(dish.getName());
        dto.setCategory(dish.getCategory());
        dto.setIsAvailable(dish.getIsAvailable());
        return dto;
    }

    public DishListItemResponse toListFilterByAvailability(Dish dish) {
        DishListItemResponse dto = new DishListItemResponse();
        dto.setId(dish.getId());
        dto.setName(dish.getName());
        dto.setCategory(dish.getCategory());
        dto.setIsAvailable(dish.getIsAvailable());
        return dto;
    }

    public DishDetailResponse toDetailResponse(Dish dish) {
        DishDetailResponse dto = new DishDetailResponse();
        dto.setId(dish.getId());
        dto.setName(dish.getName());
        dto.setCategory(dish.getCategory());
        dto.setCalories(dish.getCalories());
        dto.setCurrentPrice(dish.getCurrentPrice());
        dto.setIsAvailable(dish.getIsAvailable());
        dto.setIsVegetarian(dish.getIsVegetarian());
        return dto;
    }

    public Dish toEntity(DishRequest dto) {
        Dish dish = new Dish();
        dish.setName(dto.getName());
        dish.setCategory(dto.getCategory());
        dish.setCalories(dto.getCalories());
        dish.setCurrentPrice(dto.getCurrentPrice());
        dish.setIsAvailable(dto.getIsAvailable());
        dish.setIsVegetarian(dto.getIsVegetarian());
        return dish;
    }

    public void updateFromRequest(Dish dish, DishRequest dishRequest) {
        dish.setName(dishRequest.getName());
        dish.setCategory(dishRequest.getCategory());
        dish.setCalories(dishRequest.getCalories());
        dish.setCurrentPrice(dishRequest.getCurrentPrice());
        dish.setIsAvailable(dishRequest.getIsAvailable());
        dish.setIsVegetarian(dishRequest.getIsVegetarian());
    }
}
