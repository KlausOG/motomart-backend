package com.ecommerce.motomart.Services;

import com.ecommerce.motomart.DTO.AccessoryDTO;
import com.ecommerce.motomart.Exceptions.AccessoryNotFoundException;
import com.ecommerce.motomart.Models.Accessory;
import com.ecommerce.motomart.Models.AccessoryCategory;
import com.ecommerce.motomart.Models.Brand;
import com.ecommerce.motomart.Models.Product;
import com.ecommerce.motomart.Models.ProductAccessory;
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
    private ProductService productService;

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
        return convertToDTO(accessory);
    }

    public AccessoryDTO createAccessory(AccessoryDTO accessoryDTO) {
        Accessory accessory = convertToEntity(accessoryDTO);
        return convertToDTO(accessoryRepository.save(accessory));
    }

    public AccessoryDTO updateAccessory(Long id, AccessoryDTO accessoryDTO) {
        Accessory accessory = accessoryRepository.findById(id)
                .orElseThrow(() -> new AccessoryNotFoundException(id));

        accessory.setName(accessoryDTO.getName());
        accessory.setDescription(accessoryDTO.getDescription());

        if (accessoryDTO.getCategory() != null) {
            accessory.setCategory(AccessoryCategory.valueOf(accessoryDTO.getCategory()));
        }

        // Clear existing product associations
        accessory.getProductAccessories().clear();

        // Add new product associations
        if (accessoryDTO.getProductIds() != null) {
            for (Long productId : accessoryDTO.getProductIds()) {
                Product product = productService.findById(productId);
                if (product != null) {
                    ProductAccessory productAccessory = new ProductAccessory();
                    productAccessory.setProduct(product);
                    productAccessory.setAccessory(accessory);
                    accessory.getProductAccessories().add(productAccessory);
                }
            }
        }

        if (accessoryDTO.getBrandId() != null) {
            Brand brand = brandService.findById(accessoryDTO.getBrandId());
            accessory.setBrand(brand);
        }

        return convertToDTO(accessoryRepository.save(accessory));
    }

    public void deleteAccessory(Long id) {
        if (!accessoryRepository.existsById(id)) {
            throw new AccessoryNotFoundException(id);
        }
        accessoryRepository.deleteById(id);
    }

    private AccessoryDTO convertToDTO(Accessory accessory) {
        return new AccessoryDTO(
                accessory.getAccessoryId(),
                accessory.getName(),
                accessory.getDescription(),
                accessory.getProductAccessories().stream()
                        .map(pa -> pa.getProduct().getProductId())
                        .collect(Collectors.toList()),
                accessory.getCategory() != null ? accessory.getCategory().name() : null,
                accessory.getBrand() != null ? accessory.getBrand().getBrandId() : null
        );
    }

    private Accessory convertToEntity(AccessoryDTO accessoryDTO) {
        Accessory accessory = new Accessory();
        accessory.setName(accessoryDTO.getName());
        accessory.setDescription(accessoryDTO.getDescription());

        if (accessoryDTO.getCategory() != null) {
            accessory.setCategory(AccessoryCategory.valueOf(accessoryDTO.getCategory()));
        } else {
            throw new IllegalArgumentException("Accessory category must not be null");
        }

        if (accessoryDTO.getBrandId() != null) {
            Brand brand = brandService.findById(accessoryDTO.getBrandId());
            accessory.setBrand(brand);
        }

        accessory.setProductAccessories(new ArrayList<>()); // Initialize productAccessories list

        return accessory;
    }
}
