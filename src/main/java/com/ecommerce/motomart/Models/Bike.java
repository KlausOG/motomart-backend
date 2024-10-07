package com.ecommerce.motomart.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bike") // Table name is 'bike'
public class Bike { // Renamed class from Product to Bike
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bikeId; // Updated field name to bikeId

    private String name;
    private String description;
    private Double price;

    @ManyToOne
    @JoinColumn(name = "showroom_id")
    private Showroom showroom;

    @ManyToOne
    @JoinColumn(name = "bikeCategory_id")
    private BikeCategory bikeCategory;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @OneToMany(mappedBy = "bike") // Updated to reference bike
    private List<Specification> specifications;

    @OneToMany(mappedBy = "bike") // Updated to reference bike
    private List<Variant> variants;

    @OneToMany(mappedBy = "bike", cascade = CascadeType.ALL, orphanRemoval = true) // Updated to reference bike
    private List<ProductAccessory> bikeAccessories = new ArrayList<>(); // Updated to reference bike

    @OneToMany(mappedBy = "bike") // Updated to reference bike
    private List<Review> reviews;

    @OneToMany(mappedBy = "bike") // Updated to reference bike
    private List<OrderItem> orderItems;

    // New field for product image
    private String imageUrl; // URL of the bike image

    // New field for visit count
    @Column(name = "visit_count")
    private Long visitCount = 0L; // Initialize to 0

    // New field for creation date
    @Column(name = "created_on")
    @Temporal(TemporalType.DATE) // Use only the date part
    private Date createdOn;

    // New field for body style
    @Column(name = "body_style")
    @Enumerated(EnumType.STRING)
    private BodyStyle bodyStyle; // Body style (e.g., Cruiser, Sports, Scooty, Street, Super)

    @PrePersist
    protected void onCreate() {
        createdOn = new Date(); // Set the current date when the entity is created
    }

    // Method to increment visit count
    public void incrementVisitCount() {
        this.visitCount++;
    }
}
