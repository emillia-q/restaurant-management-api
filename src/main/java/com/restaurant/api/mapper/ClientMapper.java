package com.restaurant.api.mapper;

import com.restaurant.api.dto.response.ClientResponse;
import com.restaurant.api.entities.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {
    public ClientResponse toDTO(Client client) {
        ClientResponse dto = new ClientResponse();
        dto.setId(client.getId());
        dto.setName(client.getName());
        dto.setLastName(client.getLastName());
        return dto;
    }
}
