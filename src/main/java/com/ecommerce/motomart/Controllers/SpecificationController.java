package com.ecommerce.motomart.Controllers;

import com.ecommerce.motomart.DTO.SpecificationDTO;
import com.ecommerce.motomart.Exceptions.SpecificationNotFoundException;
import com.ecommerce.motomart.Services.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/specifications")
public class SpecificationController {

    @Autowired
    private SpecificationService specificationService;

    @GetMapping
    public List<SpecificationDTO> getAllSpecifications() {
        return specificationService.getAllSpecifications();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpecificationDTO> getSpecificationById(@PathVariable Long id) {
        SpecificationDTO specification = specificationService.getSpecificationById(id);
        return ResponseEntity.ok(specification);
    }

    @PostMapping
    public ResponseEntity<SpecificationDTO> createSpecification(@RequestBody SpecificationDTO specificationDTO) {
        SpecificationDTO createdSpecification = specificationService.createSpecification(specificationDTO);
        return ResponseEntity.status(201).body(createdSpecification); // Return 201 Created
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpecificationDTO> updateSpecification(@PathVariable Long id, @RequestBody SpecificationDTO specificationDetails) {
        SpecificationDTO updatedSpecification = specificationService.updateSpecification(id, specificationDetails);
        return ResponseEntity.ok(updatedSpecification);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpecification(@PathVariable Long id) {
        specificationService.deleteSpecification(id);
        return ResponseEntity.noContent().build();
    }
}
