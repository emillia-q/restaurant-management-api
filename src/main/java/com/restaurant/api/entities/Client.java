package com.restaurant.api.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private Integer id;

    private String name;

    private String lastName;

    private String phoneNumber;

    private String email;

    private String defaultDeliveryAddress;

    private Integer loyaltyPoints = 0;

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