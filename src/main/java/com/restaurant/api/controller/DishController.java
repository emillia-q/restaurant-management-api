package com.restaurant.api.controller;

import com.restaurant.api.dto.request.DishRequest;
import com.restaurant.api.dto.response.DishCreatedResponse;
import com.restaurant.api.dto.response.DishListItemResponse;
import com.restaurant.api.service.DishService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get dish by id")
    @ApiResponse(responseCode = "200")
    @ApiResponse(responseCode = "404", description = "Dish not found")
    public DishListItemResponse findById(@PathVariable Long id) {
        return dishService.findDishById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Add new dish")
    @ApiResponse(responseCode = "201", description = "Dish created")
    public DishCreatedResponse newDish(@RequestBody DishRequest dishRequest) {
        return dishService.addDish(dishRequest);
    }
}
