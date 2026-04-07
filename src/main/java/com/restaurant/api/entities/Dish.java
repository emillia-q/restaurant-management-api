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
@Table(name = "dishes")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Dish {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
    
    private String category;
    
    private Integer calories;
    
    private Double currentPrice;

    // Default available
    private Boolean isAvailable = true;

    private Boolean isVegetarian = false;

    public void toggleAvailability() {
        this.isAvailable = !this.isAvailable;
    }
    
    public Double calculateCost() {
        return this.currentPrice; 
    }
	
	
}
