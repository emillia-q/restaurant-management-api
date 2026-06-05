package com.restaurant.api.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.restaurant.api.enums.OrderStatus;
import com.restaurant.api.enums.OrderType;
import com.restaurant.api.enums.PaymentMethod;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@JsonPropertyOrder({
        "id",
        "orderDateTime",
        "orderStatus",
        "paymentMethod",
        "type",
        "clientId",
        "deliveryAddress",
        "totalPrice"
})
public class OrderDetailResponse {
    private Long id;

    private LocalDateTime orderDateTime;

    private OrderStatus orderStatus;

    private PaymentMethod paymentMethod;

    private OrderType type;

    private Long clientId;

    private String deliveryAddress;

    private Double totalPrice;

    private List<OrderItemResponse> items;
}
