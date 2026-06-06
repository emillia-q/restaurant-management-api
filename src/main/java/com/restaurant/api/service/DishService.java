package com.restaurant.api.service;

import com.restaurant.api.dto.request.DishRequest;
import com.restaurant.api.dto.response.DishCreatedResponse;
import com.restaurant.api.dto.response.DishListItemResponse;
import com.restaurant.api.entities.Dish;
import com.restaurant.api.exception.BadRequestException;
import com.restaurant.api.exception.ItemNotFoundException;
import com.restaurant.api.mapper.DishMapper;
import com.restaurant.api.repository.DishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DishService {
    private final DishRepository dishRepository;
    private final DishMapper dishMapper;

    public List<DishListItemResponse> getAllDishes() {
        return dishRepository.findAll().stream()
                .map(dishMapper::toListItemResponse)
                .toList();
    }

    public DishListItemResponse findDishById(Long id) {
        Dish dish = dishRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(Dish.class, id));
        return dishMapper.toListItemResponse(dish);
    }

    public DishCreatedResponse addDish(DishRequest dishRequest) {
        if (dishRepository.existsByName(dishRequest.getName()))
            throw new BadRequestException("Dish with name: " + dishRequest.getName() + " already exists");
        Dish dish = dishMapper.toEntity(dishRequest);
        Dish saved = dishRepository.save(dish);
        return dishMapper.toCreatedResponse(saved);
    }

    public DishCreatedResponse updateDish(Long id, DishRequest dishRequest) {
        Dish dish = dishRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(Dish.class, id));

        // Check if new name is not taken by another dish
        if (!dish.getName().equals(dishRequest.getName()) && dishRepository.existsByName(dishRequest.getName()))
            throw new BadRequestException("Dish with name: " + dishRequest.getName() + " already exists");

        dishMapper.updateFromRequest(dish, dishRequest);
        Dish saved = dishRepository.save(dish);
        return dishMapper.toCreatedResponse(saved);
    }

    public void deleteDish(Long id) {
        Dish dish = dishRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(Dish.class, id));
        dishRepository.delete(dish);
    }
}
