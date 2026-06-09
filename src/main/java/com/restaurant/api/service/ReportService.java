package com.restaurant.api.service;

import com.restaurant.api.dto.response.report.DailySalesReportResponse;
import com.restaurant.api.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final OrderRepository orderRepository;

    public DailySalesReportResponse getAmount(LocalDate date) {
        Double amount = orderRepository.getDailySalesAmount(date);
        double finalAmount =  (amount!=null) ? amount : 0.0;
        return new DailySalesReportResponse(date, finalAmount);
    }
}
