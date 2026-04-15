package com.restaurant.api.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "orders")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime orderDateTime;

    private String orderStatus;

    private Double totalPrice;

    private String paymentMethod;

    private String deliveryAddress;

    private String type;

    public void updateStatus(String newStatus) {
        this.orderStatus = newStatus;
    }

    public Double calculateTotal() {
        return this.totalPrice;
    }

    public void cancelOrder() {
        this.orderStatus = "CANCELLED";
    }
}