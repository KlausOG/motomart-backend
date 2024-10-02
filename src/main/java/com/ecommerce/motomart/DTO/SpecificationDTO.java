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
public class SpecificationDTO {
    private Long id;

    @NotBlank
    private String specs;

    private String value;

    private Long productId; // Assuming specification is linked to a product

    // Getters and Setters
}
