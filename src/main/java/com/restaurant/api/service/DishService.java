package com.restaurant.api.service;

import com.restaurant.api.dto.request.DishRequest;
import com.restaurant.api.dto.response.DishDetailResponse;
import com.restaurant.api.dto.response.DishListItemResponse;
import com.restaurant.api.entities.Dish;
import com.restaurant.api.enums.DishCategory;
import com.restaurant.api.exception.BadRequestException;
import com.restaurant.api.exception.ItemNotFoundException;
import com.restaurant.api.exception.ResourceInUseException;
import com.restaurant.api.mapper.DishMapper;
import com.restaurant.api.repository.DishRepository;
import com.restaurant.api.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DishService {
    private final DishRepository dishRepository;
    private final OrderItemRepository orderItemRepository;
    private final DishMapper dishMapper;

    public List<DishListItemResponse> getAllDishes(DishCategory category, Boolean isAvailable) {
        List<Dish> listOfDishes;
        if(category!=null && isAvailable!=null)
            listOfDishes = dishRepository.findByCategoryAndIsAvailable(category,isAvailable);
        else if(category!=null)
            listOfDishes = dishRepository.findByCategory(category);
        else if(isAvailable!=null)
            listOfDishes = dishRepository.findByIsAvailable(isAvailable);
        else
            listOfDishes = dishRepository.findAll();

        return listOfDishes.stream()
                .map(dishMapper::toListItemResponse)
                .toList();
    }

    public DishDetailResponse findDishById(Long id) {
        Dish dish = dishRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(Dish.class, id));
        return dishMapper.toDetailResponse(dish);
    }

    public DishDetailResponse addDish(DishRequest dishRequest) {
        if (dishRepository.existsByName(dishRequest.getName()))
            throw new BadRequestException("Dish with name: " + dishRequest.getName() + " already exists");
        Dish dish = dishMapper.toEntity(dishRequest);
        Dish saved = dishRepository.save(dish);
        return dishMapper.toDetailResponse(saved);
    }

    public DishDetailResponse updateDish(Long id, DishRequest dishRequest) {
        Dish dish = dishRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(Dish.class, id));

        // Check if new name is not taken by another dish
        if (!dish.getName().equals(dishRequest.getName()) && dishRepository.existsByName(dishRequest.getName()))
            throw new BadRequestException("Dish with name: " + dishRequest.getName() + " already exists");

        dishMapper.updateFromRequest(dish, dishRequest);
        Dish saved = dishRepository.save(dish);
        return dishMapper.toDetailResponse(saved);
    }

    public void deleteDish(Long id) {
        Dish dish = dishRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(Dish.class, id));

        if(orderItemRepository.existsByDishId(id))
            throw new ResourceInUseException("Cannot delete dish because it is part of existing orders");

        dishRepository.delete(dish);
    }
}
