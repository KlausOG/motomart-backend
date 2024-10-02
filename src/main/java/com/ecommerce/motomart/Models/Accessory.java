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
@Table(name = "accessories")
public class Accessory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accessoryId;

    private String name;
    private String description;

    @ManyToMany(mappedBy = "accessories")
    private List<Product> products;

    // Getters and Setters
}
