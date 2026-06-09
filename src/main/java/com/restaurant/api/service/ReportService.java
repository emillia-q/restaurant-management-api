package com.restaurant.api.service;

import com.restaurant.api.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final OrderRepository orderRepository;

    public Double getAmount(LocalDate date) {
        Double amount = orderRepository.getDailySalesAmount(date);
        return (amount!=null) ? amount : 0.0;
    }
}
