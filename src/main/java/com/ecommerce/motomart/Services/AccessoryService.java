package com.ecommerce.motomart.Services;

import com.ecommerce.motomart.DTO.AccessoryDTO;
import com.ecommerce.motomart.Exceptions.AccessoryNotFoundException;
import com.ecommerce.motomart.Models.*;
import com.ecommerce.motomart.Repositories.AccessoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccessoryService {

    @Autowired
    private AccessoryRepository accessoryRepository;

    @Autowired
    private BikeService bikeService;

    @Autowired
    private BrandService brandService;

    public List<AccessoryDTO> getAllAccessories() {
        return accessoryRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public AccessoryDTO getAccessoryById(Long id) {
        Accessory accessory = accessoryRepository.findById(id)
                .orElseThrow(() -> new AccessoryNotFoundException(id));
        incrementVisitCount(accessory); // Increment visit count when accessed
        return convertToDTO(accessory);
    }

    public AccessoryDTO createAccessory(AccessoryDTO accessoryDTO) {
        Accessory accessory = convertToEntity(accessoryDTO);
        return convertToDTO(accessoryRepository.save(accessory)); // Save and convert to DTO
    }

    public AccessoryDTO updateAccessory(Long id, AccessoryDTO accessoryDTO) {
        Accessory accessory = accessoryRepository.findById(id)
                .orElseThrow(() -> new AccessoryNotFoundException(id));

        // Update accessory fields
        accessory.setName(accessoryDTO.getName());
        accessory.setDescription(accessoryDTO.getDescription());
        accessory.setImageUrl(accessoryDTO.getImageUrl());
        accessory.setPrice(accessoryDTO.getPrice());

        if (accessoryDTO.getCategory() != null) {
            accessory.setCategory(AccessoryCategory.valueOf(accessoryDTO.getCategory()));
        }

        // Clear existing Bike associations
        accessory.getProductAccessories().clear();

        // Add new Bike associations
        if (accessoryDTO.getBikeIds() != null) {
            for (Long bikeId : accessoryDTO.getBikeIds()) {
                Bike bike = bikeService.findById(bikeId);
                if (bike != null) {
                    ProductAccessory productAccessory = new ProductAccessory();
                    productAccessory.setBike(bike);
                    productAccessory.setAccessory(accessory);
                    accessory.getProductAccessories().add(productAccessory);
                }
            }
        }

        if (accessoryDTO.getBrandId() != null) {
            Brand brand = brandService.findById(accessoryDTO.getBrandId());
            accessory.setBrand(brand);
        }

        return convertToDTO(accessoryRepository.save(accessory)); // Save and convert to DTO
    }

    public void deleteAccessory(Long id) {
        if (!accessoryRepository.existsById(id)) {
            throw new AccessoryNotFoundException(id);
        }
        accessoryRepository.deleteById(id);
    }

    private void incrementVisitCount(Accessory accessory) {
        accessory.incrementVisitCount(); // Increment visit count
        accessoryRepository.save(accessory); // Save the updated accessory
    }

    private AccessoryDTO convertToDTO(Accessory accessory) {
        return new AccessoryDTO(
                accessory.getAccessoryId(),
                accessory.getName(),
                accessory.getDescription(),
                accessory.getProductAccessories().stream()
                        .map(pa -> pa.getBike().getBikeId())
                        .collect(Collectors.toList()),
                accessory.getCategory() != null ? accessory.getCategory().name() : null,
                accessory.getBrand() != null ? accessory.getBrand().getBrandId() : null,
                accessory.getImageUrl(),
                accessory.getPrice(),
                accessory.getCreatedOn(),
                accessory.getVisitCount() // Include the visit count
        );
    }

    private Accessory convertToEntity(AccessoryDTO accessoryDTO) {
        Accessory accessory = new Accessory();
        accessory.setName(accessoryDTO.getName());
        accessory.setDescription(accessoryDTO.getDescription());
        accessory.setImageUrl(accessoryDTO.getImageUrl());
        accessory.setPrice(accessoryDTO.getPrice());

        if (accessoryDTO.getCategory() != null) {
            accessory.setCategory(AccessoryCategory.valueOf(accessoryDTO.getCategory()));
        } else {
            throw new IllegalArgumentException("Accessory category must not be null");
        }

        if (accessoryDTO.getBrandId() != null) {
            Brand brand = brandService.findById(accessoryDTO.getBrandId());
            accessory.setBrand(brand);
        }

        accessory.setProductAccessories(new ArrayList<>()); // Initialize ProductAccessories list

        return accessory;
    }
}
