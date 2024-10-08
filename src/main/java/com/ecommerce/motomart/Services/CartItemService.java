package com.ecommerce.motomart.Services;

import com.ecommerce.motomart.DTO.CartItemDTO;
import com.ecommerce.motomart.Exceptions.CartItemNotFoundException;
import com.ecommerce.motomart.Models.CartItem;
import com.ecommerce.motomart.Models.Cart;
import com.ecommerce.motomart.Models.Bike;
import com.ecommerce.motomart.Repositories.CartItemRepository;
import com.ecommerce.motomart.Repositories.CartRepository;
import com.ecommerce.motomart.Repositories.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private BikeRepository bikeRepository;

    public List<CartItemDTO> getAllCartItems() {
        return cartItemRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public CartItemDTO getCartItemById(Long id) {
        CartItem cartItem = cartItemRepository.findById(id)
                .orElseThrow(() -> new CartItemNotFoundException(id));
        return convertToDTO(cartItem);
    }

    public CartItemDTO createCartItem(CartItemDTO cartItemDTO) {
        CartItem cartItem = convertToEntity(cartItemDTO);
        return convertToDTO(cartItemRepository.save(cartItem));
    }

    public CartItemDTO updateCartItem(Long id, CartItemDTO cartItemDTO) {
        CartItem cartItem = cartItemRepository.findById(id)
                .orElseThrow(() -> new CartItemNotFoundException(id));

        // Update fields as needed
        cartItem.setQuantity(cartItemDTO.getQuantity());
        // Set the Bike as needed, assuming you handle fetching the Bike

        return convertToDTO(cartItemRepository.save(cartItem));
    }

    public void deleteCartItem(Long id) {
        if (!cartItemRepository.existsById(id)) {
            throw new CartItemNotFoundException(id);
        }
        cartItemRepository.deleteById(id);
    }

    private CartItemDTO convertToDTO(CartItem cartItem) {
        return new CartItemDTO(
                cartItem.getCartItemId(),
                cartItem.getBike().getBikeId(), // Assuming you have a BikeId field in CartItemDTO
                cartItem.getQuantity()
        );
    }

    private CartItem convertToEntity(CartItemDTO cartItemDTO) {
        CartItem cartItem = new CartItem();

        // Fetch the Bike using the BikeId from the DTO
        Bike bike = bikeRepository.findById(cartItemDTO.getBikeId())
                .orElseThrow(() -> new RuntimeException("Bike not found"));

        // Set the Bike and quantity in the CartItem
        cartItem.setBike(bike);
        cartItem.setQuantity(cartItemDTO.getQuantity());

        return cartItem;
    }
}
