package com.ecommerce.motomart.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class BikeDTO { // Renamed from ProductDTO to BikeDTO
    private Long bikeId; // Updated field name from productId to bikeId

    @NotBlank
    private String name;

    private String description;

    @NotNull // Ensure that price is specified
    private Double price;

    private Long showroomId; // Assuming Showroom is related by ID

    private String imageUrl; // Field for image URL

    // Field for visit count
    private Long visitCount; // To track how many times the bike has been visited

    // Field for creation date
    private Date createdOn; // Date when the bike was created

    // New field for body style
    private String bodyStyle; // To categorize the bike based on its body style

    // Optional: You might want to include a constructor with essential fields for easier instantiation
    public BikeDTO(Long bikeId, String name, String description, Double price, Long showroomId, String imageUrl, Long visitCount, Date createdOn, String bodyStyle) {
        this.bikeId = bikeId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.showroomId = showroomId;
        this.imageUrl = imageUrl;
        this.visitCount = visitCount;
        this.createdOn = createdOn;
        this.bodyStyle = bodyStyle;
    }
}
