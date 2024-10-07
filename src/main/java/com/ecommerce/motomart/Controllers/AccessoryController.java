package com.ecommerce.motomart.Controllers;

import com.ecommerce.motomart.DTO.AccessoryDTO;
import com.ecommerce.motomart.Exceptions.AccessoryNotFoundException;
import com.ecommerce.motomart.Exceptions.BikeNotFoundException;
import com.ecommerce.motomart.Services.AccessoryService;
import com.ecommerce.motomart.Services.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/accessories")
public class AccessoryController {

    @Autowired
    private AccessoryService accessoryService;

    @Autowired
    private BikeService bikeService;

    @GetMapping
    public ResponseEntity<List<AccessoryDTO>> getAllAccessories() {
        List<AccessoryDTO> accessories = accessoryService.getAllAccessories();
        return ResponseEntity.ok(accessories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccessoryDTO> getAccessoryById(@PathVariable Long id) {
        AccessoryDTO accessoryDTO = accessoryService.getAccessoryById(id);
        return ResponseEntity.ok(accessoryDTO);
    }

    @PostMapping
    public ResponseEntity<String> createAccessory(@RequestBody AccessoryDTO accessoryDTO) {
        // Validate category
        if (accessoryDTO.getCategory() == null) {
            return ResponseEntity.badRequest().body("Accessory category must not be null.");
        }

        // Validate bike IDs
        if (!areBikeIdsValid(accessoryDTO.getBikeIds())) {
            return ResponseEntity.badRequest().body("One or more bike IDs are invalid.");
        }

        AccessoryDTO createdAccessory = accessoryService.createAccessory(accessoryDTO);
        return ResponseEntity.status(201).body("Accessory created successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAccessory(@PathVariable Long id, @RequestBody AccessoryDTO accessoryDTO) {
        // Validate category
        if (accessoryDTO.getCategory() == null) {
            return ResponseEntity.badRequest().body("Accessory category must not be null.");
        }

        // Validate bike IDs
        if (!areBikeIdsValid(accessoryDTO.getBikeIds())) {
            return ResponseEntity.badRequest().body("One or more bike IDs are invalid.");
        }

        try {
            AccessoryDTO updatedAccessory = accessoryService.updateAccessory(id, accessoryDTO);
            return ResponseEntity.ok(updatedAccessory);
        } catch (AccessoryNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccessory(@PathVariable Long id) {
        try {
            accessoryService.deleteAccessory(id);
            return ResponseEntity.noContent().build();
        } catch (AccessoryNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    // Helper method to validate bike IDs
    private boolean areBikeIdsValid(List<Long> bikeIds) {
        if (bikeIds != null) {
            for (Long bikeId : bikeIds) {
                try {
                    bikeService.findById(bikeId); // This will throw an exception if invalid
                } catch (BikeNotFoundException e) {
                    return false; // Invalid bike ID found
                }
            }
        }
        return true; // All bike IDs are valid
    }
}
