package com.ecommerce.motomart.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "specifications")
public class Specification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long specificationId;

    @ManyToOne
    @JoinColumn(name = "bike_id")
    private Bike bike;

    private String specs;
    private String value;

    // Additional attributes for category-specific specifications
    private String categorySpecificInfo; // Optional, add as needed

    // Getters and Setters
}
