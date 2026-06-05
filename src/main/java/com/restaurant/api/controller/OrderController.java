package com.restaurant.api.controller;

import com.restaurant.api.dto.request.OrderRequest;
import com.restaurant.api.dto.response.OrderCreatedResponse;
import com.restaurant.api.dto.response.OrderDetailResponse;
import com.restaurant.api.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/orders")
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get order by id")
    @ApiResponse(responseCode = "200")
    @ApiResponse(responseCode = "404")
    public OrderDetailResponse finById(@PathVariable Long id) {
        return orderService.findOrderById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Add new order for client")
    @ApiResponse(responseCode = "201", description = "Order added")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "404", description = "Client not found")
    public OrderCreatedResponse add(@RequestBody OrderRequest orderRequest) {
        return orderService.addOrder(orderRequest);
    }
}
