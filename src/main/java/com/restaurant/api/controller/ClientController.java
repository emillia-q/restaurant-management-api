package com.restaurant.api.controller;

import com.restaurant.api.dto.response.ClientResponse;
import com.restaurant.api.entities.Client;
import com.restaurant.api.mapper.ClientMapper;
import com.restaurant.api.repository.ClientRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/clients")
public class ClientController {

    private final ClientRepository repository;
    private final ClientMapper mapper;

    ClientController(ClientRepository repository, ClientMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @GetMapping
    public List<ClientResponse> all() {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .toList();
    }
}
