package com.restaurant.api.service;

import com.restaurant.api.dto.response.report.DailySalesReportResponse;
import com.restaurant.api.dto.response.report.PopularDishReportResponse;
import com.restaurant.api.repository.DishRepository;
import com.restaurant.api.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final OrderRepository orderRepository;
    private final DishRepository dishRepository;

    public DailySalesReportResponse getAmount(LocalDate date) {
        Double amount = orderRepository.getDailySalesAmount(date);
        double finalAmount =  (amount!=null) ? amount : 0.0;
        return new DailySalesReportResponse(date, finalAmount);
    }

    public List<PopularDishReportResponse> getPopularDishes(YearMonth month) {
        LocalDateTime start = month.atDay(1).atStartOfDay();
        LocalDateTime end = month.plusMonths(1).atDay(1).atStartOfDay();

        return dishRepository.findPopularDishes(start, end);
    }
}
