package com.restaurant.api.entities;

import com.restaurant.api.enums.OrderStatus;
import com.restaurant.api.enums.OrderType;
import com.restaurant.api.enums.PaymentMethod;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private Double totalPrice;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    private String deliveryAddress;

    @Enumerated(EnumType.STRING)
    private OrderType type;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    public void updateStatus(OrderStatus newStatus) {
        this.orderStatus = newStatus;
    }

    public Double calculateTotal() {
        return this.totalPrice;
    }

    public void cancelOrder() {
        this.orderStatus = OrderStatus.CANCELLED;
    }
}
