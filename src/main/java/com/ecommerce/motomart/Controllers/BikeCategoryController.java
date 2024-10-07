package com.ecommerce.motomart.Controllers;

import com.ecommerce.motomart.DTO.BikeCategoryDTO;
import com.ecommerce.motomart.Services.BikeCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/categories")
public class BikeCategoryController {

    @Autowired
    private BikeCategoryService bikeCategoryService;

    @GetMapping
    public List<BikeCategoryDTO> getAllCategories() {
        return bikeCategoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BikeCategoryDTO> getCategoryById(@PathVariable Long id) {
        BikeCategoryDTO bikeCategoryDTO = bikeCategoryService.getCategoryById(id);
        return ResponseEntity.ok(bikeCategoryDTO);
    }

    @PostMapping
    public ResponseEntity<BikeCategoryDTO> createCategory(@RequestBody BikeCategoryDTO bikeCategoryDTO) {
        BikeCategoryDTO createdCategory = bikeCategoryService.createCategory(bikeCategoryDTO);
        return ResponseEntity.status(201).body(createdCategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BikeCategoryDTO> updateCategory(@PathVariable Long id, @RequestBody BikeCategoryDTO bikeCategoryDTO) {
        BikeCategoryDTO updatedCategory = bikeCategoryService.updateCategory(id, bikeCategoryDTO);
        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        bikeCategoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
