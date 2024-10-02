package com.ecommerce.motomart.Repositories;


import com.ecommerce.motomart.Models.Accessory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessoryRepository extends JpaRepository<Accessory, Long> {
    // Custom query methods can be added here
}

