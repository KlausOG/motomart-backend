package com.ecommerce.motomart.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bike_categories")
public class BikeCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    private String name; // E.g., "Electric", "Petrol", "Hybrid"

    @OneToMany(mappedBy = "bikeCategory")
    private List<Bike> bikes; // List of products associated with this category

    // Additional attributes as needed
}
