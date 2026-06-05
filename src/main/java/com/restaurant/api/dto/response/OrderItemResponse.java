package com.restaurant.api.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({
        "dishId",
        "dishName",
        "quantity",
        "priceAtPurchase"
})
public class OrderItemResponse {
    private Long dishId;
    private String dishName;
    private Integer quantity;
    private Double priceAtPurchase;
}
