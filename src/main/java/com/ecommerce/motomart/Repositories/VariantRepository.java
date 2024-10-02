package com.ecommerce.motomart.Repositories;

import com.ecommerce.motomart.Models.Variant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VariantRepository extends JpaRepository<Variant, Long> {
    // Custom query methods can be added here
}

