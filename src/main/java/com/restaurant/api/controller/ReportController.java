package com.restaurant.api.controller;

import com.restaurant.api.dto.response.report.DailySalesReportResponse;
import com.restaurant.api.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/reports")
public class ReportController {
    private final ReportService reportService;

    @GetMapping("/daily-sales")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get daily sales amount")
    @ApiResponse(responseCode = "200")
    public DailySalesReportResponse amount(@RequestParam LocalDate date) {
        return reportService.getAmount(date);
    }
}
