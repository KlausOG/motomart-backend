package com.ecommerce.motomart.Controllers;

import com.ecommerce.motomart.Exceptions.SpecificationNotFoundException;
import com.ecommerce.motomart.Models.Specification;
import com.ecommerce.motomart.Repositories.SpecificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/specifications")
public class SpecificationController {

    @Autowired
    private SpecificationRepository specificationRepository;

    @GetMapping
    public List<Specification> getAllSpecifications() {
        return specificationRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Specification> getSpecificationById(@PathVariable Long id) {
        return specificationRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new SpecificationNotFoundException(id));
    }

    @PostMapping
    public Specification createSpecification(@RequestBody Specification specification) {
        return specificationRepository.save(specification);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Specification> updateSpecification(@PathVariable Long id, @RequestBody Specification specificationDetails) {
        return specificationRepository.findById(id)
                .map(specification -> {
                    specification.setSpecs(specificationDetails.getSpecs());
                    specification.setValue(specificationDetails.getValue());
                    return ResponseEntity.ok(specificationRepository.save(specification));
                })
                .orElseThrow(() -> new SpecificationNotFoundException(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpecification(@PathVariable Long id) {
        if (!specificationRepository.existsById(id)) {
            throw new SpecificationNotFoundException(id);
        }
        specificationRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
