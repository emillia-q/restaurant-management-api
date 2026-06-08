package com.restaurant.api.repository;

import com.restaurant.api.dto.response.DishListItemResponse;
import com.restaurant.api.entities.Dish;
import com.restaurant.api.enums.DishCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DishRepository extends JpaRepository<Dish, Long> {
    boolean existsByName(String name);
    boolean existsById(Long id);
    List<Dish> findByCategory(DishCategory category);
}
