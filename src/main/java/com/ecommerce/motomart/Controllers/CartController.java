package com.ecommerce.motomart.Controllers;

import com.ecommerce.motomart.Exceptions.CartNotFoundException;
import com.ecommerce.motomart.Models.Cart;
import com.ecommerce.motomart.Repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    @Autowired
    private CartRepository cartRepository;

    @GetMapping
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cart> getCartById(@PathVariable Long id) {
        return cartRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new CartNotFoundException(id));
    }

    @PostMapping
    public Cart createCart(@RequestBody Cart cart) {
        return cartRepository.save(cart);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cart> updateCart(@PathVariable Long id, @RequestBody Cart cartDetails) {
        return cartRepository.findById(id)
                .map(cart -> {
                    // Update cart details if needed
                    return ResponseEntity.ok(cartRepository.save(cart));
                })
                .orElseThrow(() -> new CartNotFoundException(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long id) {
        if (!cartRepository.existsById(id)) {
            throw new CartNotFoundException(id);
        }
        cartRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
