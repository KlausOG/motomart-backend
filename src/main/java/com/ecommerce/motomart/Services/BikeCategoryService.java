package com.ecommerce.motomart.Services;

import com.ecommerce.motomart.DTO.BikeCategoryDTO;
import com.ecommerce.motomart.Exceptions.CategoryNotFoundException;
import com.ecommerce.motomart.Models.BikeCategory;
import com.ecommerce.motomart.Repositories.BikeCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BikeCategoryService {

    @Autowired
    private BikeCategoryRepository bikeCategoryRepository;

    public List<BikeCategoryDTO> getAllCategories() {
        return bikeCategoryRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public BikeCategoryDTO getCategoryById(Long id) {
        BikeCategory bikeCategory = bikeCategoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
        return convertToDTO(bikeCategory);
    }

    public BikeCategoryDTO createCategory(BikeCategoryDTO bikeCategoryDTO) {
        BikeCategory bikeCategory = convertToEntity(bikeCategoryDTO);
        return convertToDTO(bikeCategoryRepository.save(bikeCategory));
    }

    public BikeCategoryDTO updateCategory(Long id, BikeCategoryDTO bikeCategoryDTO) {
        BikeCategory bikeCategory = bikeCategoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
        bikeCategory.setName(bikeCategoryDTO.getName());
        return convertToDTO(bikeCategoryRepository.save(bikeCategory));
    }

    public void deleteCategory(Long id) {
        if (!bikeCategoryRepository.existsById(id)) {
            throw new CategoryNotFoundException(id);
        }
        bikeCategoryRepository.deleteById(id);
    }

    private BikeCategoryDTO convertToDTO(BikeCategory bikeCategory) {
        return new BikeCategoryDTO(
                bikeCategory.getCategoryId(),
                bikeCategory.getName()
        );
    }

    private BikeCategory convertToEntity(BikeCategoryDTO bikeCategoryDTO) {
        BikeCategory bikeCategory = new BikeCategory();
        bikeCategory.setName(bikeCategoryDTO.getName());
        return bikeCategory;
    }
}
