package com.restaurant.api.service;

import com.restaurant.api.dto.request.ClientRequest;
import com.restaurant.api.dto.response.ClientDetailResponse;
import com.restaurant.api.dto.response.ClientListItemResponse;
import com.restaurant.api.entities.Client;
import com.restaurant.api.exception.BadRequestException;
import com.restaurant.api.exception.ItemNotFoundException;
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

    private void sanitizeClientData(Client client) {
        // Name
        String formattedName = client.getName().trim().substring(0, 1).toUpperCase()
                + client.getName().trim().substring(1).toLowerCase();
        client.setName(formattedName);

        // Last name
        String formattedLastName = client.getLastName().trim().substring(0, 1).toUpperCase()
                + client.getLastName().trim().substring(1).toLowerCase();
        client.setLastName(formattedLastName);

        // Email
        String formattedEmail = client.getEmail().trim().toLowerCase();
        client.setEmail(formattedEmail);
    }

    public List<ClientListItemResponse> getAllClients() {
        return clientRepository.findAll().stream()
                .map(clientMapper::toListItemResponse)
                .toList();
    }

    public ClientDetailResponse findClientById(Long id) {
        return clientRepository.findById(id)
                .map(clientMapper::toDetailResponse)
                .orElseThrow(() -> new ItemNotFoundException(Client.class, id));
    }

    public ClientDetailResponse addClient(ClientRequest clientRequest) {
        if (clientRepository.existsByEmailIgnoreCase(clientRequest.getEmail()))
            throw new BadRequestException("Client with email: " + clientRequest.getEmail().trim().toLowerCase() + " already exists");
        if (clientRepository.existsByPhoneNumber(clientRequest.getPhoneNumber()))
            throw new BadRequestException("Client with phone number: " + clientRequest.getPhoneNumber() + " already exists");

        Client client = clientMapper.toEntity(clientRequest);
        sanitizeClientData(client);
        Client saved = clientRepository.save(client);
        return clientMapper.toDetailResponse(saved);
    }

    public ClientDetailResponse updateClient(Long id, ClientRequest clientRequest) {
        Client client =  clientRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(Client.class, id));

        // Check if new email & phone nb are not taken by another client
        if (!client.getEmail().equalsIgnoreCase(clientRequest.getEmail()) && clientRepository.existsByEmailIgnoreCase(clientRequest.getEmail()))
            throw new BadRequestException("Client with email: " + clientRequest.getEmail().trim().toLowerCase() + " already exists");
        if (!client.getPhoneNumber().equals(clientRequest.getPhoneNumber()) && clientRepository.existsByPhoneNumber(clientRequest.getPhoneNumber()))
            throw new BadRequestException("Client with phone number: " + clientRequest.getPhoneNumber() + " already exists");

        clientMapper.updateFromRequest(client, clientRequest);
        sanitizeClientData(client);
        Client saved = clientRepository.save(client);
        return clientMapper.toDetailResponse(saved);
    }

    public void deleteClient(Long id) {
        Client client = clientRepository.findById(id)
                        .orElseThrow(() -> new ItemNotFoundException(Client.class, id));
        clientRepository.delete(client);
    }
}
