package com.restaurant.api.assembler;

import com.restaurant.api.controller.ClientController;
import com.restaurant.api.dto.response.ClientDetailResponse;
import com.restaurant.api.dto.response.hateoas.ClientResource;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ClientModelAssembler extends RepresentationModelAssemblerSupport<ClientDetailResponse, ClientResource> {

    public ClientModelAssembler() {
        super(ClientController.class, ClientResource.class);
    }

    @Override
    public ClientResource toModel(ClientDetailResponse entity) {
        ClientResource model = new ClientResource(
                entity.getId(),
                entity.getName(),
                entity.getLastName(),
                entity.getPhoneNumber(),
                entity.getEmail(),
                entity.getDefaultDeliveryAddress(),
                entity.getLoyaltyPoints()
        );

        model.add(
                linkTo(methodOn(ClientController.class).getOrdersForClient(entity.getId())).withRel("orders")
        );

        return model;
    }
}
