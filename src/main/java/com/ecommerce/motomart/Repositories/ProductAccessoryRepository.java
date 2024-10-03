package com.ecommerce.motomart.Repositories;

import com.ecommerce.motomart.Models.ProductAccessory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductAccessoryRepository extends JpaRepository<ProductAccessory, Long> {
    // You can define custom query methods here if needed
}
