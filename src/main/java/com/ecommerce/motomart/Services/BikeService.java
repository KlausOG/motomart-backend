package com.ecommerce.motomart.Services;

import com.ecommerce.motomart.DTO.BikeDTO;
import com.ecommerce.motomart.Exceptions.BikeNotFoundException;
import com.ecommerce.motomart.Models.Bike;
import com.ecommerce.motomart.Models.BodyStyle;
import com.ecommerce.motomart.Models.Showroom;
import com.ecommerce.motomart.Repositories.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BikeService {

    @Autowired
    private BikeRepository bikeRepository;

    public List<BikeDTO> getAllBikes() {
        return bikeRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public BikeDTO getBikeById(Long id) {
        Bike bike = findById(id);
        incrementVisitCount(bike);
        return convertToDTO(bike);
    }

    public BikeDTO createBike(BikeDTO bikeDTO) {
        Bike bike = convertToEntity(bikeDTO);
        return convertToDTO(bikeRepository.save(bike));
    }

    public BikeDTO updateBike(Long id, BikeDTO bikeDTO) {
        Bike bike = findById(id);
        bike.setName(bikeDTO.getName());
        bike.setDescription(bikeDTO.getDescription());
        bike.setPrice(bikeDTO.getPrice());
        bike.setImageUrl(bikeDTO.getImageUrl());

        // Set showroom based on ID if provided
        if (bikeDTO.getShowroomId() != null) {
            Showroom showroom = new Showroom();
            showroom.setShowroomId(bikeDTO.getShowroomId());
            bike.setShowroom(showroom);
        }

        return convertToDTO(bikeRepository.save(bike));
    }

    public void deleteBike(Long id) {
        if (!bikeRepository.existsById(id)) {
            throw new BikeNotFoundException(id);
        }
        bikeRepository.deleteById(id);
    }

    public Bike findById(Long id) {
        return bikeRepository.findById(id)
                .orElseThrow(() -> new BikeNotFoundException(id));
    }

    private void incrementVisitCount(Bike bike) {
        bike.incrementVisitCount(); // Using the increment method
        bikeRepository.save(bike);
    }

    private BikeDTO convertToDTO(Bike bike) {
        return new BikeDTO(
                bike.getBikeId(),
                bike.getName(),
                bike.getDescription(),
                bike.getPrice(),
                bike.getShowroom() != null ? bike.getShowroom().getShowroomId() : null,
                bike.getImageUrl(),
                bike.getVisitCount(),
                bike.getCreatedOn(),
                bike.getBodyStyle() != null ? bike.getBodyStyle().name() : null // Include body style if needed
        );
    }

    private Bike convertToEntity(BikeDTO bikeDTO) {
        Bike bike = new Bike();
        bike.setName(bikeDTO.getName());
        bike.setDescription(bikeDTO.getDescription());
        bike.setPrice(bikeDTO.getPrice());
        bike.setImageUrl(bikeDTO.getImageUrl());
        bike.setVisitCount(0L); // Initialize visit count
        bike.setCreatedOn(new Date()); // Set current date

        // Set showroom based on ID
        if (bikeDTO.getShowroomId() != null) {
            Showroom showroom = new Showroom();
            showroom.setShowroomId(bikeDTO.getShowroomId());
            bike.setShowroom(showroom);
        }

        // Optionally set body style if you have it in DTO
        if (bikeDTO.getBodyStyle() != null) {
            bike.setBodyStyle(BodyStyle.valueOf(bikeDTO.getBodyStyle()));
        }

        return bike;
    }
}
