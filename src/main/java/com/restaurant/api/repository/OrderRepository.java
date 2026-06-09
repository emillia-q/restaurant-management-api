package com.restaurant.api.repository;

import com.restaurant.api.dto.response.report.OrdersByTypeReportResponse;
import com.restaurant.api.entities.Order;
import com.restaurant.api.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findByClientId(Long clientId);
    List<Order> findByOrderStatus(OrderStatus orderStatus);

    // Report scenarios
    @Query("select sum(o.totalPrice) from Order o " +
            "where o.orderStatus != 'CANCELLED' " +
            "and cast(o.orderDateTime as localdate) = :date")
    Double getDailySalesAmount(LocalDate date);

    @Query("select new com.restaurant.api.dto.response.report.OrdersByTypeReportResponse(o.type, count(o)) " +
            "from Order o " +
            "group by o.type " +
            "order by count(o) desc")
    List<OrdersByTypeReportResponse> countOrdersByType();
}
