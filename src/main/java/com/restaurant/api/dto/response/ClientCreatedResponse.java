package com.restaurant.api.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({
        "id",
        "name",
        "lastName",
        "phoneNumber",
        "email",
        "defaultDeliveryAddress",
        "loyaltyPoints"
})
public class ClientCreatedResponse {
    private Long id;

    private String name;

    private String lastName;

    private String phoneNumber;

    private String email;

    private String defaultDeliveryAddress;

    private Integer loyaltyPoints;
}
