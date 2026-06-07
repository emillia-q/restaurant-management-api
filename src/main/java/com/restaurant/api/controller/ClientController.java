package com.restaurant.api.controller;

import com.restaurant.api.assembler.ClientModelAssembler;
import com.restaurant.api.dto.request.ClientRequest;
import com.restaurant.api.dto.response.ClientDetailResponse;
import com.restaurant.api.dto.response.ClientListItemResponse;
import com.restaurant.api.dto.response.OrderDetailResponse;
import com.restaurant.api.dto.response.hateoas.ClientResource;
import com.restaurant.api.service.ClientService;
import com.restaurant.api.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/clients")
public class ClientController {

    private final ClientService clientService;
    private final OrderService orderService;
    private final ClientModelAssembler clientModelAssembler;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get the list of clients")
    @ApiResponse(responseCode = "200", description = "List of clients")
    public List<ClientListItemResponse> all() {
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get client by id with HATEOAS links")
    @ApiResponse(responseCode = "200")
    @ApiResponse(responseCode = "404", description = "Client not found")
    public ClientResource findById(@PathVariable Long id) {
        ClientDetailResponse dto = clientService.findClientById(id);
        return clientModelAssembler.toModel(dto);
    }

    @GetMapping("/{id}/orders")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get list of orders for client")
    @ApiResponse(responseCode = "200")
    @ApiResponse(responseCode = "404", description = "Client not found")
    public List<OrderDetailResponse> getOrdersForClient(@PathVariable Long id) {
        return orderService.getOrdersByClientId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Add new client")
    @ApiResponse(responseCode = "201", description = "Client created")
    @ApiResponse(responseCode = "400", description = "Invalid input data")
    public ClientDetailResponse newClient(@Valid @RequestBody ClientRequest clientRequest)  {
        return clientService.addClient(clientRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update client")
    @ApiResponse(responseCode = "200")
    @ApiResponse(responseCode = "400", description = "Invalid input data")
    @ApiResponse(responseCode = "404", description = "Client not found")
    public ClientDetailResponse update(@PathVariable Long id, @Valid @RequestBody ClientRequest clientRequest) {
        return clientService.updateClient(id, clientRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete client")
    @ApiResponse(responseCode = "204")
    @ApiResponse(responseCode = "404", description = "Client not found")
    public void delete(@PathVariable Long id) {
        clientService.deleteClient(id);
    }
}
