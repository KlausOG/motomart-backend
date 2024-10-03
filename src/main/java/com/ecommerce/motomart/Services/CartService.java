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

        // Update the user based on the userId provided in the DTO
        if (cartDTO.getUserId() != null) {
            User user = userRepository.findById(cartDTO.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            cart.setUser(user);
        }

        // If you want to update cart items, handle that here
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
                cart.getCartItems().stream().map(this::convertCartItemToDTO).collect(Collectors.toList())
        );
    }

    private Cart convertToEntity(CartDTO cartDTO) {
        Cart cart = new Cart();
        // Fetch user based on userId if necessary
        // cart.setUser(userRepository.findById(cartDTO.getUserId()).orElseThrow(() -> new RuntimeException("User not found")));
        return cart;
    }

    private CartItemDTO convertCartItemToDTO(CartItem cartItem) {
        return new CartItemDTO(
                cartItem.getCartItemId(),
                cartItem.getProduct().getProductId(), // Assuming you have a productId field in CartItemDTO
                cartItem.getQuantity()
        );
    }

    private CartItem convertToEntity(CartItemDTO cartItemDTO) {
        CartItem cartItem = new CartItem();
        // Fetch user based on userId if necessary
        // cart.setUser(userRepository.findById(cartDTO.getUserId()).orElseThrow(() -> new RuntimeException("User not found")));
        return cartItem;
    }
}
