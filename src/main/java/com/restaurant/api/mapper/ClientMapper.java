package com.restaurant.api.mapper;

import com.restaurant.api.dto.request.ClientRequest;
import com.restaurant.api.dto.response.ClientCreatedResponse;
import com.restaurant.api.dto.response.ClientListItemResponse;
import com.restaurant.api.entities.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    // TODO: add helper method to convert first letter of name and last name to capital

    public ClientListItemResponse toListItemResponse(Client client) {
        ClientListItemResponse dto = new ClientListItemResponse();
        dto.setId(client.getId());
        dto.setName(client.getName());
        dto.setLastName(client.getLastName());
        return dto;
    }

    public ClientCreatedResponse toCreatedResponse(Client client) {
        ClientCreatedResponse dto = new ClientCreatedResponse();
        dto.setId(client.getId());
        dto.setName(client.getName());
        dto.setLastName(client.getLastName());
        dto.setPhoneNumber(client.getPhoneNumber());
        dto.setEmail(client.getEmail());
        dto.setDefaultDeliveryAddress(client.getDefaultDeliveryAddress());
        dto.setLoyaltyPoints(client.getLoyaltyPoints());
        return dto;
    }

    public Client toEntity(ClientRequest dto) {
        Client client = new Client();
        client.setName(dto.getName());
        client.setLastName(dto.getLastName());
        client.setPhoneNumber(dto.getPhoneNumber());
        client.setEmail(dto.getEmail());
        client.setDefaultDeliveryAddress(dto.getDefaultDeliveryAddress());
        return client;
    }

    public void updateFromRequest(Client client, ClientRequest dto) {
        client.setName(dto.getName());
        client.setLastName(dto.getLastName());
        client.setPhoneNumber(dto.getPhoneNumber());
        client.setEmail(dto.getEmail());
        client.setDefaultDeliveryAddress(dto.getDefaultDeliveryAddress());
    }
}
