package com.restaurant.api.dto.request;

import com.restaurant.api.enums.OrderStatus;
import com.restaurant.api.enums.OrderType;
import com.restaurant.api.enums.PaymentMethod;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class OrderRequest {
    @NotNull(message = "Order status is required")
    private OrderStatus orderStatus;

    @NotNull(message = "Payment method is required")
    private PaymentMethod paymentMethod;

    @NotNull(message = "Order type is required")
    private OrderType type;

    @NotNull(message = "Client ID is required")
    private Long clientId;

    private String deliveryAddress;

    @NotEmpty(message = "Order must contain at least one item")
    @NotNull(message = "Items list cannot be null")
    @Valid // Forces Spring to go deep into each OrderItemRequest and check its quantity
    private List<OrderItemRequest> items;
}
