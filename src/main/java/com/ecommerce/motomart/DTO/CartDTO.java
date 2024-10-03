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
    private Long userId;
    private List<CartItemDTO> cartItems; // Assuming you have this DTO
}
