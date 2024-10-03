package com.ecommerce.motomart.Services;

import com.ecommerce.motomart.DTO.BrandDTO;
import com.ecommerce.motomart.Exceptions.BrandNotFoundException;
import com.ecommerce.motomart.Models.Brand;
import com.ecommerce.motomart.Repositories.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;

    public List<BrandDTO> getAllBrands() {
        return brandRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Brand findById(Long id) {
        Optional<Brand> brand = brandRepository.findById(id);
        return brand.orElseThrow(() -> new RuntimeException("Brand not found with ID: " + id)); // Handle not found
    }
    public BrandDTO getBrandById(Long id) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new BrandNotFoundException(id));
        return convertToDTO(brand);
    }

    public BrandDTO createBrand(BrandDTO brandDTO) {
        Brand brand = convertToEntity(brandDTO);
        return convertToDTO(brandRepository.save(brand));
    }

    public BrandDTO updateBrand(Long id, BrandDTO brandDTO) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new BrandNotFoundException(id));
        brand.setName(brandDTO.getName());
        return convertToDTO(brandRepository.save(brand));
    }

    public void deleteBrand(Long id) {
        if (!brandRepository.existsById(id)) {
            throw new BrandNotFoundException(id);
        }
        brandRepository.deleteById(id);
    }

    private BrandDTO convertToDTO(Brand brand) {
        return new BrandDTO(
                brand.getBrandId(),
                brand.getName()
        );
    }

    private Brand convertToEntity(BrandDTO brandDTO) {
        Brand brand = new Brand();
        brand.setName(brandDTO.getName());
        return brand;
    }
}
