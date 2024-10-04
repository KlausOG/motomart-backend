package com.ecommerce.motomart.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long productId;

    @NotBlank
    private String name;

    private String description;

    private Double price;

    private Long showroomId; // Assuming Showroom is related by ID

    private String imageUrl; // New field for image URL

    // Getters and Setters (if using Lombok, they're generated automatically)
}
