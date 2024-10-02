package com.ecommerce.motomart.Controllers;

import com.ecommerce.motomart.Exceptions.AccessoryNotFoundException;
import com.ecommerce.motomart.Models.Accessory;
import com.ecommerce.motomart.Repositories.AccessoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accessories")
public class AccessoryController {

    @Autowired
    private AccessoryRepository accessoryRepository;

    @GetMapping
    public List<Accessory> getAllAccessories() {
        return accessoryRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Accessory> getAccessoryById(@PathVariable Long id) {
        return accessoryRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new AccessoryNotFoundException(id));
    }

    @PostMapping
    public Accessory createAccessory(@RequestBody Accessory accessory) {
        return accessoryRepository.save(accessory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Accessory> updateAccessory(@PathVariable Long id, @RequestBody Accessory accessoryDetails) {
        return accessoryRepository.findById(id)
                .map(accessory -> {
                    // Update accessory details if needed
                    return ResponseEntity.ok(accessoryRepository.save(accessory));
                })
                .orElseThrow(() -> new AccessoryNotFoundException(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccessory(@PathVariable Long id) {
        if (!accessoryRepository.existsById(id)) {
            throw new AccessoryNotFoundException(id);
        }
        accessoryRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
