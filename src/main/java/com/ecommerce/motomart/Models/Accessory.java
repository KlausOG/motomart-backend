package com.ecommerce.motomart.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accessories")
public class Accessory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accessoryId;

    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "brand_id") // Reference to brand
    private Brand brand; // Link to the brand of the accessory

    @Enumerated(EnumType.STRING)
    private AccessoryCategory category; // Enum to represent accessory categories

    @ManyToMany(mappedBy = "accessories")
    private List<Product> products = new ArrayList<>(); // Accessory can be associated with multiple products

    @OneToMany(mappedBy = "accessories")
    private List<ProductAccessory> productAccessories = new ArrayList<>();

    // Other fields and methods...
}
