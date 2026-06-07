package com.restaurant.api.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.restaurant.api.enums.OrderStatus;
import com.restaurant.api.enums.OrderType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonPropertyOrder({
        "id",
        "orderDateTime",
        "orderStatus",
        "type",
        "clientFullName",
        "totalPrice",
        "itemsCount"
})
public class OrderListItemResponse {
    private Long id;
    private LocalDateTime orderDateTime;
    private OrderStatus orderStatus;
    private Double totalPrice;
    private OrderType type;
    private String clientFullName;
    private Integer itemsCount;
}
