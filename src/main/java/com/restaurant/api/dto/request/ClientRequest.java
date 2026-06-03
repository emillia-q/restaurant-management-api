package com.restaurant.api.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientRequest {
    private String name;

    private String lastName;

    private String phoneNumber;

    private String email;

    private String defaultDeliveryAddress;
}
