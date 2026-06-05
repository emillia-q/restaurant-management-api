package com.restaurant.api.dto.request;

import com.restaurant.api.enums.OrderStatus;
import com.restaurant.api.enums.OrderType;
import com.restaurant.api.enums.PaymentMethod;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class OrderRequest {
    private OrderStatus orderStatus;

    private PaymentMethod paymentMethod;

    private OrderType type;

    private Long clientId;

    private String deliveryAddress;

    private List<OrderItemRequest> items;
}
