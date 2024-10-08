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
public class VariantDTO {
    private Long id;

    @NotBlank
    private String name;

    private Long bikeId; // Assuming variant is linked to a product

    private Double additionalPrice;
    // Getters and Setters
}
