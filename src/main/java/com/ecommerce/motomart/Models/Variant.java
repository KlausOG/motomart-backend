package com.ecommerce.motomart.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "variants")
public class Variant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long variantId;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private String variantName;
    private Double additionalPrice;

    // Additional attributes for category-specific variants
    private String categorySpecificFeature; // Optional, add as needed

    // Getters and Setters
}
