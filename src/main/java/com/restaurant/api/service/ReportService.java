package com.restaurant.api.service;

import com.restaurant.api.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final OrderRepository orderRepository;

    public Double getAmount(LocalDateTime start, LocalDateTime end) {
        return orderRepository.getDailySalesAmount(start, end);
    }
}
