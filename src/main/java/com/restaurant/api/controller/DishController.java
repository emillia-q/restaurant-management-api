package com.restaurant.api.controller;

import com.restaurant.api.dto.response.DishListItemResponse;
import com.restaurant.api.service.DishService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/dishes")
public class DishController {

    private final DishService dishService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get the list of dishes")
    @ApiResponse(responseCode = "200", description = "List of dishes")
    public List<DishListItemResponse> all() {
        return dishService.getAllDishes();
    }
}
