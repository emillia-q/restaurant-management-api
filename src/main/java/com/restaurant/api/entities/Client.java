package com.restaurant.api.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "clients")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 50)
    private String lastName;

    @Column(nullable = false, unique = true, length = 15)
    private String phoneNumber;

    @Column(nullable = false, unique = true)
    private String email;

    private String defaultDeliveryAddress;

    private Integer loyaltyPoints = 0;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();

    public void addPoints(Integer amount) {
        if (amount != null && amount > 0) {
            this.loyaltyPoints += amount;
        }
    }

    public Boolean usePoints(Integer amount) {
        if (amount != null && amount > 0 && this.loyaltyPoints >= amount) {
            this.loyaltyPoints -= amount;
            return true;
        }
        return false;
    }
}
