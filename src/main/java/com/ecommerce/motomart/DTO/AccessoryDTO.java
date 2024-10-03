package com.ecommerce.motomart.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccessoryDTO {
    private Long accessoryId; // Changed from 'id' to 'accessoryId'

    @NotBlank
    private String name;

    private String description;

    @NotNull // Assuming that an accessory must belong to a product
    private List<Long> productIds; // Link to the product

    @NotNull // Ensure that category is specified
    private String category; // New field for category (you might want to use an enum as well)

    @NotNull
    private Long brandId;
    // Getters and Setters (if using Lombok, they're generated automatically)
}
