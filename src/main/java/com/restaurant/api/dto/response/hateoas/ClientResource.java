package com.restaurant.api.dto.response.hateoas;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ClientResource extends RepresentationModel<ClientResource> {
    private final Long id;

    private final String name;

    private final String lastName;

    private final String phoneNumber;

    private final String email;

    private final String defaultDeliveryAddress;

    private final Integer loyaltyPoints;
}
