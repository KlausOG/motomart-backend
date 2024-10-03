package com.ecommerce.motomart.Controllers;

import com.ecommerce.motomart.DTO.CartDTO;
import com.ecommerce.motomart.Services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4202")
@RestController
@RequestMapping("/api/carts")
public class CartController {


    @Autowired
    private CartService cartService;

    @CrossOrigin(origins = "http://localhost:4202")
    @GetMapping
    public List<CartDTO> getAllCarts() {
        return cartService.getAllCarts();
    }

    @CrossOrigin(origins = "http://localhost:4202")
    @GetMapping("/{id}")
    public ResponseEntity<CartDTO> getCartById(@PathVariable Long id) {
        CartDTO cartDTO = cartService.getCartById(id);
        return ResponseEntity.ok(cartDTO);
    }

    @CrossOrigin(origins = "http://localhost:4202")
    @PostMapping
    public ResponseEntity<CartDTO> createCart(@RequestBody CartDTO cartDTO) {
        CartDTO createdCart = cartService.createCart(cartDTO);
        return ResponseEntity.status(201).body(createdCart); // Return 201 Created
    }

    @CrossOrigin(origins = "http://localhost:4202")
    @PutMapping("/{id}")
    public ResponseEntity<CartDTO> updateCart(@PathVariable Long id, @RequestBody CartDTO cartDTO) {
        CartDTO updatedCart = cartService.updateCart(id, cartDTO);
        return ResponseEntity.ok(updatedCart);
    }

    @CrossOrigin(origins = "http://localhost:4202")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long id) {
        cartService.deleteCart(id);
        return ResponseEntity.noContent().build();
    }
}
