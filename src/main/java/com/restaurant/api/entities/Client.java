package com.restaurant.api.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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

    private String name;

    private String lastName;

    private String phoneNumber;

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
