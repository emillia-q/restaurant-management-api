package com.restaurant.api.service;

import com.restaurant.api.dto.request.ClientRequest;
import com.restaurant.api.dto.response.ClientCreatedResponse;
import com.restaurant.api.dto.response.ClientListItemResponse;
import com.restaurant.api.entities.Client;
import com.restaurant.api.exception.ClientNotFoundException;
import com.restaurant.api.mapper.ClientMapper;
import com.restaurant.api.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public List<ClientListItemResponse> getAllClients() {
        return clientRepository.findAll().stream()
                .map(clientMapper::toListItemResponse)
                .toList();
    }

    public ClientListItemResponse findClientById(Long id) {
        return clientRepository.findById(id)
                .map(clientMapper::toListItemResponse)
                .orElseThrow(() -> new ClientNotFoundException(id));
    }

    public ClientCreatedResponse addClient(ClientRequest clientRequest) {
        Client client = clientMapper.toEntity(clientRequest);
        Client saved = clientRepository.save(client);
        return clientMapper.toCreatedResponse(saved);
    }

    public ClientCreatedResponse updateClient(Long id, ClientRequest clientRequest) {
        Client client =  clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(id));
        clientMapper.updateFromRequest(client, clientRequest);
        Client saved = clientRepository.save(client);
        return clientMapper.toCreatedResponse(saved);
    }
}
