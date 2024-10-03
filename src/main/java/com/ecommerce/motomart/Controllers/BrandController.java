package com.ecommerce.motomart.Controllers;

import com.ecommerce.motomart.DTO.BrandDTO;
import com.ecommerce.motomart.Services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4202")
@RestController
@RequestMapping("/api/brands")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @CrossOrigin(origins = "http://localhost:4202")
    @GetMapping
    public List<BrandDTO> getAllBrands() {
        return brandService.getAllBrands();
    }

    @CrossOrigin(origins = "http://localhost:4202")
    @GetMapping("/{id}")
    public ResponseEntity<BrandDTO> getBrandById(@PathVariable Long id) {
        BrandDTO brandDTO = brandService.getBrandById(id);
        return ResponseEntity.ok(brandDTO);
    }

    @CrossOrigin(origins = "http://localhost:4202")
    @PostMapping
    public ResponseEntity<BrandDTO> createBrand(@RequestBody BrandDTO brandDTO) {
        BrandDTO createdBrand = brandService.createBrand(brandDTO);
        return ResponseEntity.status(201).body(createdBrand); // Return 201 Created
    }

    @CrossOrigin(origins = "http://localhost:4202")
    @PutMapping("/{id}")
    public ResponseEntity<BrandDTO> updateBrand(@PathVariable Long id, @RequestBody BrandDTO brandDTO) {
        BrandDTO updatedBrand = brandService.updateBrand(id, brandDTO);
        return ResponseEntity.ok(updatedBrand);
    }


    @CrossOrigin(origins = "http://localhost:4202")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBrand(@PathVariable Long id) {
        brandService.deleteBrand(id);
        return ResponseEntity.noContent().build();
    }
}
