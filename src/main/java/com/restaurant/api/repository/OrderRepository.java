package com.restaurant.api.repository;

import com.restaurant.api.entities.Order;
import com.restaurant.api.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findByClientId(Long clientId);
    List<Order> findByOrderStatus(OrderStatus orderStatus);
}
