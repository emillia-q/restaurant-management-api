package com.restaurant.api.controller;

import com.restaurant.api.dto.request.ClientRequest;
import com.restaurant.api.dto.response.ClientCreatedResponse;
import com.restaurant.api.dto.response.ClientListItemResponse;
import com.restaurant.api.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/clients")
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get the list of clients")
    @ApiResponse(responseCode = "200", description = "List of clients")
    public List<ClientListItemResponse> all() {
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get client by id")
    @ApiResponse(responseCode = "200")
    @ApiResponse(responseCode = "404", description = "Client not found")
    public ClientListItemResponse findById(@PathVariable Long id) {
        return clientService.findClientById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Add new client")
    @ApiResponse(responseCode = "201", description = "Client created")
    public ClientCreatedResponse newClient(@RequestBody ClientRequest clientRequest)  {
        return clientService.addClient(clientRequest);
    }
}
