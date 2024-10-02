package com.ecommerce.motomart.Repositories;

import com.ecommerce.motomart.Models.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecificationRepository extends JpaRepository<Specification, Long> {
    // Custom query methods can be added here
}
