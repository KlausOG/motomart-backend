package com.ecommerce.motomart.Services;

import com.ecommerce.motomart.DTO.CartDTO;
import com.ecommerce.motomart.DTO.CartItemDTO;
import com.ecommerce.motomart.Exceptions.CartNotFoundException;
import com.ecommerce.motomart.Models.Cart;
import com.ecommerce.motomart.Models.CartItem;
import com.ecommerce.motomart.Models.User;
import com.ecommerce.motomart.Repositories.CartRepository;
import com.ecommerce.motomart.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    public List<CartDTO> getAllCarts() {
        return cartRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public CartDTO getCartById(Long id) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new CartNotFoundException(id));
        return convertToDTO(cart);
    }

    public CartDTO createCart(CartDTO cartDTO) {
        Cart cart = convertToEntity(cartDTO);
        return convertToDTO(cartRepository.save(cart));
    }

    public CartDTO updateCart(Long id, CartDTO cartDTO) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new CartNotFoundException(id));

        // Update user
        if (cartDTO.getUserId() != null) {
            User user = userRepository.findById(cartDTO.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            cart.setUser(user);
        }

        // Update cart items
        if (cartDTO.getCartItems() != null) {
            List<CartItem> updatedCartItems = cartDTO.getCartItems().stream()
                    .map(this::convertToEntity) // Convert each CartItemDTO to CartItem
                    .collect(Collectors.toList());
            cart.setCartItems(updatedCartItems);
        }

        // Save the updated cart
        return convertToDTO(cartRepository.save(cart));
    }

    public void deleteCart(Long id) {
        if (!cartRepository.existsById(id)) {
            throw new CartNotFoundException(id);
        }
        cartRepository.deleteById(id);
    }

    private CartDTO convertToDTO(Cart cart) {
        return new CartDTO(
                cart.getCartId(),
                cart.getUser() != null ? cart.getUser().getUserId() : null,
                cart.getCartItems().stream().map(this::convertCartItemToDTO).collect(Collectors.toList()),
                null, // Assuming shipping address is not part of Cart
                null  // Assuming payment method is not part of Cart
        );
    }

    private Cart convertToEntity(CartDTO cartDTO) {
        Cart cart = new Cart();
        if (cartDTO.getUserId() != null) {
            User user = userRepository.findById(cartDTO.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            cart.setUser(user);
        }
        return cart;
    }

    private CartItemDTO convertCartItemToDTO(CartItem cartItem) {
        return new CartItemDTO(
                cartItem.getCartItemId(),
                cartItem.getBike() != null ? cartItem.getBike().getBikeId() : null, // Handle null Bike case
                cartItem.getQuantity()
        );
    }

    private CartItem convertToEntity(CartItemDTO cartItemDTO) {
        CartItem cartItem = new CartItem();
        // You might want to set the bike here if you have a bikeId in DTO
        return cartItem;
    }
}
