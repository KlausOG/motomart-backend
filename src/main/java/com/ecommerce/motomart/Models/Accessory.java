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
@Table(name = "accessories")
public class Accessory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accessoryId;

    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @Enumerated(EnumType.STRING) // Store enum as a string in the database
    @Column(name = "category") // Optional: Specify column name if needed
    private AccessoryCategory category;

    @OneToMany(mappedBy = "accessory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductAccessory> productAccessories = new ArrayList<>();

    // New field for image
    private String imageUrl; // URL or path to the accessory image

    // New field for price
    private Double price;

    // New field for creation date
    @Column(name = "created_on")
    @Temporal(TemporalType.DATE) // Use only the date part
    private Date createdOn;

    // New field for visit count
    @Column(name = "visit_count")
    private Long visitCount = 0L; // Initialize with zero

    @PrePersist
    protected void onCreate() {
        createdOn = new Date(); // Set the current date
    }

    // Method to increment visit count
    public void incrementVisitCount() {
        this.visitCount++;
    }
}
