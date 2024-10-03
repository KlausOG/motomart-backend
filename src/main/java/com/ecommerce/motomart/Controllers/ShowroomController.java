package com.ecommerce.motomart.Controllers;

import com.ecommerce.motomart.DTO.ShowroomDTO;
import com.ecommerce.motomart.Exceptions.ShowroomNotFoundException;
import com.ecommerce.motomart.Services.ShowroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/showrooms")
public class ShowroomController {

    @Autowired
    private ShowroomService showroomService;

    @GetMapping
    public List<ShowroomDTO> getAllShowrooms() {
        return showroomService.getAllShowrooms();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShowroomDTO> getShowroomById(@PathVariable Long id) {
        ShowroomDTO showroom = showroomService.getShowroomById(id);
        return ResponseEntity.ok(showroom);
    }

    @PostMapping
    public ResponseEntity<ShowroomDTO> createShowroom(@RequestBody ShowroomDTO showroomDTO) {
        ShowroomDTO createdShowroom = showroomService.createShowroom(showroomDTO);
        return ResponseEntity.status(201).body(createdShowroom); // Return 201 Created
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShowroomDTO> updateShowroom(@PathVariable Long id, @RequestBody ShowroomDTO showroomDetails) {
        ShowroomDTO updatedShowroom = showroomService.updateShowroom(id, showroomDetails);
        return ResponseEntity.ok(updatedShowroom);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShowroom(@PathVariable Long id) {
        showroomService.deleteShowroom(id);
        return ResponseEntity.noContent().build();
    }
}
