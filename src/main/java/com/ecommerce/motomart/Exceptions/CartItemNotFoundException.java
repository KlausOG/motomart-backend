package com.ecommerce.motomart.Exceptions;

public class CartItemNotFoundException extends RuntimeException {
    public CartItemNotFoundException(Long cartItemId) {
        super("Cart item not found: " + cartItemId);
    }
}
