package com.ecommerce.motomart.Controllers;

import com.ecommerce.motomart.DTO.BikeDTO;
import com.ecommerce.motomart.Exceptions.BikeNotFoundException;
import com.ecommerce.motomart.Services.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/bikes")
public class BikeController {

    @Autowired
    private BikeService bikeService;

    @GetMapping
    public ResponseEntity<List<BikeDTO>> getAllBikes() {
        List<BikeDTO> bikes = bikeService.getAllBikes();
        return ResponseEntity.ok(bikes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BikeDTO> getBikeById(@PathVariable Long id) {
        try {
            BikeDTO bikeDTO = bikeService.getBikeById(id);
            return ResponseEntity.ok(bikeDTO);
        } catch (BikeNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<BikeDTO> createBike(@RequestBody BikeDTO bikeDTO) {
        BikeDTO createdBike = bikeService.createBike(bikeDTO);
        return ResponseEntity.status(201).body(createdBike);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BikeDTO> updateBike(@PathVariable Long id, @RequestBody BikeDTO bikeDTO) {
        try {
            BikeDTO updatedBike = bikeService.updateBike(id, bikeDTO);
            return ResponseEntity.ok(updatedBike);
        } catch (BikeNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBike(@PathVariable Long id) {
        try {
            bikeService.deleteBike(id);
            return ResponseEntity.noContent().build();
        } catch (BikeNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
