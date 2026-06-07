package com.restaurant.api.service;

import com.restaurant.api.dto.request.OrderItemRequest;
import com.restaurant.api.dto.request.OrderRequest;
import com.restaurant.api.dto.response.OrderCreatedResponse;
import com.restaurant.api.dto.response.OrderDetailResponse;
import com.restaurant.api.dto.response.OrderListItemResponse;
import com.restaurant.api.entities.Client;
import com.restaurant.api.entities.Dish;
import com.restaurant.api.entities.Order;
import com.restaurant.api.entities.OrderItem;
import com.restaurant.api.enums.OrderType;
import com.restaurant.api.exception.BadRequestException;
import com.restaurant.api.exception.ItemNotFoundException;
import com.restaurant.api.mapper.OrderMapper;
import com.restaurant.api.repository.ClientRepository;
import com.restaurant.api.repository.DishRepository;
import com.restaurant.api.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final DishRepository dishRepository;
    private final OrderMapper orderMapper;

    public List<OrderListItemResponse> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(orderMapper::toListItemResponse)
                .toList();
    }

    public OrderCreatedResponse addOrder(OrderRequest orderRequest) {
        // Check if cielnt exists
        Client client = clientRepository.findById(orderRequest.getClientId())
                .orElseThrow(() -> new ItemNotFoundException(Client.class, orderRequest.getClientId()));

        // Set missing entity fields
        Order order = orderMapper.toEntity(orderRequest);
        order.setClient(client);
        order.setOrderDateTime(LocalDateTime.now());

        if (orderRequest.getType() == OrderType.DELIVERY) {
            if (orderRequest.getDeliveryAddress() == null || orderRequest.getDeliveryAddress().isBlank())
                throw new BadRequestException("Delivery address is required for DELIVERY orders");

            order.setDeliveryAddress(orderRequest.getDeliveryAddress());
        } else {
            // Ignore address from body
            order.setDeliveryAddress(null);
        }

        for (OrderItemRequest itemReq : orderRequest.getItems()) {
            Dish dish = dishRepository.findById(itemReq.getDishId())
                    .orElseThrow(() -> new ItemNotFoundException(Dish.class, itemReq.getDishId()));
            OrderItem item = orderMapper.toOrderItem(itemReq, dish, order);
            order.getItems().add(item);
        }

        // Calculate total order price
        double total = order.getItems().stream()
                .mapToDouble(OrderItem::calculateSubtotal)
                .sum();

        order.setTotalPrice(total);

        // Save order
        Order saved = orderRepository.save(order);

        // To response
        return orderMapper.toCreatedResponse(saved);
    }

    public OrderDetailResponse findOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(Order.class, id));

        return orderMapper.toDetailResponse(order);
    }

    public List<OrderDetailResponse> getOrdersByClientId(Long id) {
        if(!clientRepository.existsById(id))
            throw new ItemNotFoundException(Client.class, id);

        return  orderRepository.findByClientId(id).stream()
                .map(orderMapper::toDetailResponse)
                .toList();
    }

    public OrderCreatedResponse updateOrder(Long id, OrderRequest orderRequest) {
        //Check if order exists
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(Order.class, id));

        // Check if cielnt exists
        Client client = clientRepository.findById(orderRequest.getClientId())
                .orElseThrow(() -> new ItemNotFoundException(Client.class, orderRequest.getClientId()));

        // Set missing entity fields
        orderMapper.updateFromRequest(order, orderRequest);
        order.setClient(client);

        if (orderRequest.getType() == OrderType.DELIVERY) {
            if (orderRequest.getDeliveryAddress() == null || orderRequest.getDeliveryAddress().isBlank())
                throw new BadRequestException("Delivery address is required for DELIVERY orders");

            order.setDeliveryAddress(orderRequest.getDeliveryAddress());
        } else {
            // Ignore address from body
            order.setDeliveryAddress(null);
        }

        order.getItems().clear();
        for (OrderItemRequest itemReq : orderRequest.getItems()) {
            Dish dish = dishRepository.findById(itemReq.getDishId())
                    .orElseThrow(() -> new ItemNotFoundException(Dish.class, itemReq.getDishId()));
            OrderItem item = orderMapper.toOrderItem(itemReq, dish, order);
            order.getItems().add(item);
        }

        // Calculate total order price
        double total = order.getItems().stream()
                .mapToDouble(OrderItem::calculateSubtotal)
                .sum();

        order.setTotalPrice(total);

        // Save order
        Order saved = orderRepository.save(order);

        return orderMapper.toCreatedResponse(saved);
    }

    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(Order.class, id));

        orderRepository.delete(order);
    }
}
