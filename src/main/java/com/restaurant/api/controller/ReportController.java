package com.restaurant.api.controller;

import com.restaurant.api.dto.response.report.DailySalesReportResponse;
import com.restaurant.api.dto.response.report.OrdersByTypeReportResponse;
import com.restaurant.api.dto.response.report.PopularDishReportResponse;
import com.restaurant.api.enums.OrderType;
import com.restaurant.api.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/reports")
public class ReportController {
    private final ReportService reportService;

    @GetMapping("/daily-sales")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get daily sales amount")
    @ApiResponse(responseCode = "200")
    public DailySalesReportResponse amount(@RequestParam(value = "date", required = false) LocalDate date) {
        return reportService.getAmount(date);
    }

    @GetMapping("/popular-dishes")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get list of popular dishes")
    @ApiResponse(responseCode = "200")
    public List<PopularDishReportResponse> getPopularDishes(@RequestParam(value = "month", required = false) YearMonth month) {
        return reportService.getPopularDishes(month);
    }

    @GetMapping("/orders-by-type")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get list of orders by type")
    @ApiResponse(responseCode = "200")
    public List<OrdersByTypeReportResponse> getOrderListByType() {
        return reportService.getOrdersByTypeReport();
    }
}
