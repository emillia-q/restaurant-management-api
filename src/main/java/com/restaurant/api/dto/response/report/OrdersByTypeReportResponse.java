package com.restaurant.api.dto.response.report;

import com.restaurant.api.enums.OrderType;

public record OrdersByTypeReportResponse(OrderType orderType, Long count) {
}
