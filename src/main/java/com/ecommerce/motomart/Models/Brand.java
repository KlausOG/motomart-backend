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
@Table(name = "brands")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long brandId;

    private String name; // Name of the brand (e.g., "Brand A", "Brand B")

    @OneToMany(mappedBy = "brand")
    private List<Bike> bikes; // Products associated with this brand

    @OneToMany(mappedBy = "brand")
    private List<Accessory> accessories; // Accessories associated with this brand
}
