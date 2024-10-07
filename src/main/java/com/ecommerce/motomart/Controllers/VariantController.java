package com.ecommerce.motomart.Controllers;

import com.ecommerce.motomart.DTO.VariantDTO;
import com.ecommerce.motomart.Exceptions.VariantNotFoundException;
import com.ecommerce.motomart.Services.VariantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/variants")
public class VariantController {

    @Autowired
    private VariantService variantService;

    @GetMapping
    public List<VariantDTO> getAllVariants() {
        return variantService.getAllVariants();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VariantDTO> getVariantById(@PathVariable Long id) {
        VariantDTO variant = variantService.getVariantById(id);
        return ResponseEntity.ok(variant);
    }

    @PostMapping
    public ResponseEntity<VariantDTO> createVariant(@RequestBody VariantDTO variantDTO) {
        VariantDTO createdVariant = variantService.createVariant(variantDTO);
        return ResponseEntity.status(201).body(createdVariant); // Return 201 Created
    }

    @PutMapping("/{id}")
    public ResponseEntity<VariantDTO> updateVariant(@PathVariable Long id, @RequestBody VariantDTO variantDetails) {
        VariantDTO updatedVariant = variantService.updateVariant(id, variantDetails);
        return ResponseEntity.ok(updatedVariant);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVariant(@PathVariable Long id) {
        variantService.deleteVariant(id);
        return ResponseEntity.noContent().build();
    }
}
