package com.restaurant.api.mapper;

import com.restaurant.api.dto.request.OrderRequest;
import com.restaurant.api.dto.response.OrderCreatedResponse;
import com.restaurant.api.entities.Order;
import org.springframework.stereotype.Component;

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
        order.setTotalPrice(dto.getTotalPrice());
        order.setDeliveryAddress(dto.getDeliveryAddress());
        return order;
    }
}
