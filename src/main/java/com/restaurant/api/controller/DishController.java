package com.restaurant.api.controller;

import com.restaurant.api.assembler.DishModelAssembler;
import com.restaurant.api.dto.request.DishRequest;
import com.restaurant.api.dto.response.DishDetailResponse;
import com.restaurant.api.dto.response.DishListItemResponse;
import com.restaurant.api.dto.response.RecipeResponse;
import com.restaurant.api.dto.response.hateoas.DishResource;
import com.restaurant.api.enums.DishCategory;
import com.restaurant.api.service.DishService;
import com.restaurant.api.service.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/dishes")
public class DishController {

    private final DishService dishService;
    private final RecipeService recipeService;
    private final DishModelAssembler dishModelAssembler;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get the list of dishes")
    @ApiResponse(responseCode = "200", description = "List of dishes")
    public List<DishListItemResponse> all() {
        return dishService.getAllDishes();
    }

    @GetMapping("/category")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Filter list of dishes by dish category")
    @ApiResponse(responseCode = "200", description = "Filtered list of dishes")
    @ApiResponse(responseCode = "400", description = "Wrong dish category")
    public List<DishListItemResponse> filterByDishCategory(@RequestParam DishCategory category) {
        return dishService.getDishesWithFilterCategory(category);
    }

    @GetMapping("/availability")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Filter list of dishes by availability")
    @ApiResponse(responseCode = "200", description = "Filtered list of dishes")
    public List<DishListItemResponse> filterByIsAvailable(@RequestParam Boolean isAvailable) {
        return dishService.getDishesWithFilterAvailability(isAvailable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get dish by id with HATEOAS links")
    @ApiResponse(responseCode = "200")
    @ApiResponse(responseCode = "404", description = "Dish not found")
    public DishResource findById(@PathVariable Long id) {
        DishDetailResponse dto =  dishService.findDishById(id);
        return dishModelAssembler.toModel(dto);
    }

    @GetMapping("/{id}/recipes")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get list of recipes for dish")
    @ApiResponse(responseCode = "200")
    @ApiResponse(responseCode = "404", description = "Dish not found")
    public List<RecipeResponse> getRecipesForDish(@PathVariable Long id) {
        return recipeService.getRecipesByDishId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Add new dish")
    @ApiResponse(responseCode = "201", description = "Dish created")
    @ApiResponse(responseCode = "400", description = "Invalid input data")
    public DishDetailResponse newDish(@Valid @RequestBody DishRequest dishRequest) {
        return dishService.addDish(dishRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update dish by id")
    @ApiResponse(responseCode = "200")
    @ApiResponse(responseCode = "400", description = "Invalid input data")
    @ApiResponse(responseCode = "404", description = "Dish not found")
    public DishDetailResponse update(@PathVariable Long id, @Valid @RequestBody DishRequest dishRequest) {
        return dishService.updateDish(id, dishRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete dish by id")
    @ApiResponse(responseCode = "204")
    @ApiResponse(responseCode = "404", description = "Dish not found")
    @ApiResponse(responseCode = "409", description = "Dish is used in orders and cannot be deleted")
    public void delete(@PathVariable Long id) {
        dishService.deleteDish(id);
    }
}
