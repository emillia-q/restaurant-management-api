package com.restaurant.api.service;

import com.restaurant.api.dto.response.DishListItemResponse;
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
}
