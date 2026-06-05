package com.restaurant.api.mapper;

import com.restaurant.api.dto.request.OrderItemRequest;
import com.restaurant.api.dto.request.OrderRequest;
import com.restaurant.api.dto.response.OrderCreatedResponse;
import com.restaurant.api.dto.response.OrderDetailResponse;
import com.restaurant.api.dto.response.OrderItemResponse;
import com.restaurant.api.entities.Dish;
import com.restaurant.api.entities.Order;
import com.restaurant.api.entities.OrderItem;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMapper {
    public OrderCreatedResponse toCreatedResponse(Order order) {
        OrderCreatedResponse dto = new OrderCreatedResponse();
        dto.setId(order.getId());
        dto.setOrderStatus(order.getOrderStatus());
        dto.setPaymentMethod(order.getPaymentMethod());
        dto.setType(order.getType());
        dto.setClientId(order.getClient().getId());
        dto.setTotalPrice(order.getTotalPrice());
        return dto;
    }

    public Order toEntity(OrderRequest dto) {
        Order order = new Order();
        order.setOrderStatus(dto.getOrderStatus());
        order.setPaymentMethod(dto.getPaymentMethod());
        order.setType(dto.getType());
        return order;
    }

    public OrderItem toOrderItem(OrderItemRequest req, Dish dish, Order order) {
        OrderItem item = new OrderItem();
        item.setOrder(order);
        item.setDish(dish);
        item.setQuantity(req.getQuantity());
        item.setPriceAtPurchase(dish.getCurrentPrice());
        return item;
    }

    public OrderDetailResponse toDetailResponse(Order order) {
        OrderDetailResponse dto = new OrderDetailResponse();
        dto.setId(order.getId());
        dto.setOrderDateTime(order.getOrderDateTime());
        dto.setOrderStatus(order.getOrderStatus());
        dto.setPaymentMethod(order.getPaymentMethod());
        dto.setType(order.getType());
        dto.setClientId(order.getClient().getId());
        dto.setDeliveryAddress(order.getDeliveryAddress());
        dto.setTotalPrice(order.getTotalPrice());

        // Set list of ordered items
        List<OrderItemResponse> itemDtos = order.getItems().stream()
                .map(this::toOrderItemResponse)
                .toList();
        dto.setItems(itemDtos);

        return dto;
    }

    public OrderItemResponse toOrderItemResponse(OrderItem item) {
        OrderItemResponse dto = new OrderItemResponse();
        dto.setDishId(item.getDish().getId());
        dto.setDishName(item.getDish().getName());
        dto.setQuantity(item.getQuantity());
        dto.setPriceAtPurchase(item.getPriceAtPurchase());
        return dto;
    }
}
