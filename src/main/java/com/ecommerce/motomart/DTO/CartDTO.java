package com.ecommerce.motomart.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {
    private Long id;
    private Long userId; // Assuming Cart is associated with a User
    private List<Long> productIds; // List of product IDs in the cart

    // Getters and Setters
}
