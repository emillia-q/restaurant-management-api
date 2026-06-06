package com.restaurant.api.repository;

import com.restaurant.api.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    boolean existsByDishId(Long id);
}
