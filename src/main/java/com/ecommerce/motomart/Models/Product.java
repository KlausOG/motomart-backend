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
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String name;
    private String description;
    private Double price;

    @ManyToOne
    @JoinColumn(name = "showroom_id")
    private Showroom showroom;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "brand_id") // Reference to brand
    private Brand brand; // Link to the brand of the product

    @OneToMany(mappedBy = "product")
    private List<Specification> specifications;

    @OneToMany(mappedBy = "product")
    private List<Variant> variants;

    @ManyToMany
    @JoinTable(
            name = "product_accessories",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "accessory_id")
    )
    private List<Accessory> accessories = new ArrayList<>(); // Initialize with ArrayList

    @OneToMany(mappedBy = "product")
    private List<Review> reviews;

    @OneToMany(mappedBy = "product")
    private List<OrderItem> orderItems;

    @OneToMany(mappedBy = "product")
    private List<ProductAccessory> productAccessories = new ArrayList<>();

}
