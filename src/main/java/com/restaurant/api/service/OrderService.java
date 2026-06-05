package com.restaurant.api.service;

import com.restaurant.api.dto.request.OrderItemRequest;
import com.restaurant.api.dto.request.OrderRequest;
import com.restaurant.api.dto.response.OrderCreatedResponse;
import com.restaurant.api.entities.Client;
import com.restaurant.api.entities.Dish;
import com.restaurant.api.entities.Order;
import com.restaurant.api.entities.OrderItem;
import com.restaurant.api.exception.ItemNotFoundException;
import com.restaurant.api.mapper.OrderMapper;
import com.restaurant.api.repository.ClientRepository;
import com.restaurant.api.repository.DishRepository;
import com.restaurant.api.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final DishRepository dishRepository;
    private final OrderMapper orderMapper;

    public OrderCreatedResponse addOrder(OrderRequest orderRequest) {
        // Check if cielnt exists
        Client client = clientRepository.findById(orderRequest.getClientId())
                .orElseThrow(() -> new ItemNotFoundException(Client.class, orderRequest.getClientId()));

        // Set missing entity fields
        Order order = orderMapper.toEntity(orderRequest);
        order.setClient(client);
        order.setOrderDateTime(LocalDateTime.now());

        for (OrderItemRequest itemReq : orderRequest.getItems()) {
            Dish dish = dishRepository.findById(itemReq.getDishId())
                    .orElseThrow(() -> new ItemNotFoundException(Dish.class, itemReq.getDishId()));
            OrderItem item = orderMapper.toOrderItem(itemReq, dish, order);
            order.getItems().add(item);
        }

        // Save order
        Order saved = orderRepository.save(order);

        // To response
        return orderMapper.toCreatedResponse(saved);
    }
}
