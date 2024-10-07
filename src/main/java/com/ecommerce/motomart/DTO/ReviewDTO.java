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
public class ReviewDTO {
    private Long id;

    @NotBlank
    private String content;

    private Integer rating; // Assuming rating is an integer value

    private Long bikeId; // Assuming review is linked to a product

    // Getters and Setters
}
