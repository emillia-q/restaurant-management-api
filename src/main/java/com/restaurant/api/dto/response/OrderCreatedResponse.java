package com.restaurant.api.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.restaurant.api.enums.OrderStatus;
import com.restaurant.api.enums.OrderType;
import com.restaurant.api.enums.PaymentMethod;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({
        "id",
        "orderStatus",
        "paymentMethod",
        "type",
        "clientId",
        "totalPrice"
})
public class OrderCreatedResponse {
    private Long id;

    private OrderStatus orderStatus;

    private PaymentMethod paymentMethod;

    private OrderType type;

    private Long clientId;

    private Double totalPrice;
}
