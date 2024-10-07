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
    private Long id; // Cart ID
    private Long userId; // ID of the user who owns the cart
    private List<CartItemDTO> cartItems; // List of items in the cart
    private String shippingAddress; // Shipping address for the order
    private String paymentMethod; // Payment method selected for the order
}
