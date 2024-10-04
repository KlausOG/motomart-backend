package com.ecommerce.motomart.Services;

import com.ecommerce.motomart.DTO.ProductDTO;
import com.ecommerce.motomart.Exceptions.ProductNotFoundException; // Custom exception for product not found
import com.ecommerce.motomart.Models.Product;
import com.ecommerce.motomart.Models.Showroom; // If applicable
import com.ecommerce.motomart.Repositories.ProductRepository;
import com.ecommerce.motomart.Repositories.ShowroomRepository; // If applicable
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ShowroomRepository showroomRepository; // Inject if needed

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        return convertToDTO(product);
    }

    // Method to find a product by ID (used in AccessoryService)
    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id)); // Reuse the custom exception
    }

    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = convertToEntity(productDTO);
        // Handle showroom association if needed
        if (productDTO.getShowroomId() != null) {
            Showroom showroom = showroomRepository.findById(productDTO.getShowroomId())
                    .orElseThrow(() -> new RuntimeException("Showroom not found"));
            product.setShowroom(showroom);
        }

        return convertToDTO(productRepository.save(product));
    }

    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setImageUrl(productDTO.getImageUrl()); // Update the image URL

        // Handle showroom association if needed
        if (productDTO.getShowroomId() != null) {
            Showroom showroom = showroomRepository.findById(productDTO.getShowroomId())
                    .orElseThrow(() -> new RuntimeException("Showroom not found"));
            product.setShowroom(showroom);
        }

        return convertToDTO(productRepository.save(product));
    }

    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException(id);
        }
        productRepository.deleteById(id);
    }

    private ProductDTO convertToDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId(product.getProductId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setImageUrl(product.getImageUrl()); // Include image URL

        if (product.getShowroom() != null) {
            productDTO.setShowroomId(product.getShowroom().getShowroomId());
        }

        return productDTO;
    }

    private Product convertToEntity(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setImageUrl(productDTO.getImageUrl()); // Set image URL

        return product;
    }
}
