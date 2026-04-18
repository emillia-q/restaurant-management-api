package com.restaurant.api.entities;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ingredients")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Integer amountInStock;

    @ElementCollection
    private List<String> allergens;

    private String storageUnit; 

    public void updateStock(Integer delta) {
        this.amountInStock += delta;
    }

    public Boolean checkAvailability(Integer required) {
        return this.amountInStock >= required;
    }
}