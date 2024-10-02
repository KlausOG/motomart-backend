package com.ecommerce.motomart.Controllers;

import com.ecommerce.motomart.Exceptions.VariantNotFoundException;
import com.ecommerce.motomart.Models.Variant;
import com.ecommerce.motomart.Repositories.VariantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/variants")
public class VariantController {

    @Autowired
    private VariantRepository variantRepository;

    @GetMapping
    public List<Variant> getAllVariants() {
        return variantRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Variant> getVariantById(@PathVariable Long id) {
        return variantRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new VariantNotFoundException(id));
    }

    @PostMapping
    public Variant createVariant(@RequestBody Variant variant) {
        return variantRepository.save(variant);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Variant> updateVariant(@PathVariable Long id, @RequestBody Variant variantDetails) {
        return variantRepository.findById(id)
                .map(variant -> {
                    // Update variant details if needed
                    return ResponseEntity.ok(variantRepository.save(variant));
                })
                .orElseThrow(() -> new VariantNotFoundException(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVariant(@PathVariable Long id) {
        if (!variantRepository.existsById(id)) {
            throw new VariantNotFoundException(id);
        }
        variantRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
