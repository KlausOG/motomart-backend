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
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @OneToMany(mappedBy = "product")
    private List<Specification> specifications;

    @OneToMany(mappedBy = "product")
    private List<Variant> variants;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductAccessory> productAccessories = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<Review> reviews;

    @OneToMany(mappedBy = "product")
    private List<OrderItem> orderItems;
}
