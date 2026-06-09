package com.restaurant.api.dto.response.report;

import java.time.LocalDate;

public record DailySalesReportResponse(LocalDate date, Double totalRevenue) {
}
