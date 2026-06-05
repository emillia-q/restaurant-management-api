package com.restaurant.api.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemRequest {
    private Long dishId;
    private Integer quantity;
}
