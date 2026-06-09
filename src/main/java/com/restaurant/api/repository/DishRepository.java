package com.restaurant.api.repository;

import com.restaurant.api.dto.response.DishListItemResponse;
import com.restaurant.api.dto.response.report.PopularDishReportResponse;
import com.restaurant.api.entities.Dish;
import com.restaurant.api.enums.DishCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface DishRepository extends JpaRepository<Dish, Long> {
    boolean existsByName(String name);
    boolean existsById(Long id);
    List<Dish> findByCategory(DishCategory category);
    List<Dish> findByIsAvailable(Boolean isAvailable);
    List<Dish> findByCategoryAndIsAvailable(DishCategory category, Boolean isAvailable);

    // Report scenarios
    @Query("select new com.restaurant.api.dto.response.report.PopularDishReportResponse(\n" +
            "d.name, sum(oi.quantity)) " +
            "from OrderItem oi " +
            "join oi.dish d " +
            "join oi.order o " +
            "where o.orderDateTime between :start and :end " +
            "group by d.name " +
            "order by sum(oi.quantity) desc")
    List<PopularDishReportResponse> findPopularDishes(LocalDateTime start, LocalDateTime end);
}
