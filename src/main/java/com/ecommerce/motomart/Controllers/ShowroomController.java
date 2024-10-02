package com.ecommerce.motomart.Controllers;

import com.ecommerce.motomart.Exceptions.ShowroomNotFoundException;
import com.ecommerce.motomart.Models.Showroom;
import com.ecommerce.motomart.Repositories.ShowroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/showrooms")
public class ShowroomController {

    @Autowired
    private ShowroomRepository showroomRepository;

    @GetMapping
    public List<Showroom> getAllShowrooms() {
        return showroomRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Showroom> getShowroomById(@PathVariable Long id) {
        return showroomRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ShowroomNotFoundException(id));
    }

    @PostMapping
    public Showroom createShowroom(@RequestBody Showroom showroom) {
        return showroomRepository.save(showroom);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Showroom> updateShowroom(@PathVariable Long id, @RequestBody Showroom showroomDetails) {
        return showroomRepository.findById(id)
                .map(showroom -> {
                    // Update showroom details if needed
                    return ResponseEntity.ok(showroomRepository.save(showroom));
                })
                .orElseThrow(() -> new ShowroomNotFoundException(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShowroom(@PathVariable Long id) {
        if (!showroomRepository.existsById(id)) {
            throw new ShowroomNotFoundException(id);
        }
        showroomRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
