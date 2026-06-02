package com.restaurant.api.controller;

import com.restaurant.api.dto.request.ClientRequest;
import com.restaurant.api.dto.response.ClientCreatedResponse;
import com.restaurant.api.dto.response.ClientListItemResponse;
import com.restaurant.api.entities.Client;
import com.restaurant.api.mapper.ClientMapper;
import com.restaurant.api.repository.ClientRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/clients")
public class ClientController {

    private final ClientRepository repository;
    private final ClientMapper mapper;

    ClientController(ClientRepository repository, ClientMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @GetMapping
    @Operation(summary = "Get the list of clients")
    public List<ClientListItemResponse> all() {
        return repository.findAll().stream()
                .map(mapper::toListItemResponse)
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Add new client")
    @ApiResponse(responseCode = "201", description = "Client created")
    public ClientCreatedResponse add(@RequestBody ClientRequest clientRequest)  {
        Client client = mapper.toEntity(clientRequest);
        Client saved = repository.save(client);
        return mapper.toCreatedResponse(saved);
    }
}
