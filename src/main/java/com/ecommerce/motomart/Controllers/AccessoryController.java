package com.ecommerce.motomart.Controllers;

import com.ecommerce.motomart.DTO.AccessoryDTO;
import com.ecommerce.motomart.Exceptions.AccessoryNotFoundException;
import com.ecommerce.motomart.Services.AccessoryService;
import com.ecommerce.motomart.Services.ProductService; // Assuming a ProductService exists
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accessories")
public class AccessoryController {

    @Autowired
    private AccessoryService accessoryService;

    @Autowired
    private ProductService productService; // Inject ProductService

    @GetMapping
    public List<AccessoryDTO> getAllAccessories() {
        return accessoryService.getAllAccessories();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccessoryDTO> getAccessoryById(@PathVariable Long id) {
        AccessoryDTO accessoryDTO = accessoryService.getAccessoryById(id);
        return ResponseEntity.ok(accessoryDTO);
    }

    @PostMapping
    public ResponseEntity<?> createAccessory(@RequestBody AccessoryDTO accessoryDTO) {
        if (accessoryDTO.getCategory() == null) {
            return ResponseEntity.badRequest().body("Accessory category must not be null");
        }

        // Validate each productId in the list
        if (accessoryDTO.getProductIds() != null) {
            for (Long productId : accessoryDTO.getProductIds()) {
                if (productService.findById(productId) == null) {
                    return ResponseEntity.badRequest().body("Invalid product ID: " + productId);
                }
            }
        }

        AccessoryDTO createdAccessory = accessoryService.createAccessory(accessoryDTO);
        return ResponseEntity.status(201).body(createdAccessory); // Return 201 Created
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAccessory(@PathVariable Long id, @RequestBody AccessoryDTO accessoryDTO) {
        if (accessoryDTO.getCategory() == null) {
            return ResponseEntity.badRequest().body("Accessory category must not be null");
        }

        // Validate each productId in the list
        if (accessoryDTO.getProductIds() != null) {
            for (Long productId : accessoryDTO.getProductIds()) {
                if (productService.findById(productId) == null) {
                    return ResponseEntity.badRequest().body("Invalid product ID: " + productId);
                }
            }
        }

        AccessoryDTO updatedAccessory = accessoryService.updateAccessory(id, accessoryDTO);
        return ResponseEntity.ok(updatedAccessory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccessory(@PathVariable Long id) {
        accessoryService.deleteAccessory(id);
        return ResponseEntity.noContent().build();
    }
}
