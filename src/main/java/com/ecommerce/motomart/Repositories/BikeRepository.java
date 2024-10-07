package com.ecommerce.motomart.Repositories;

import com.ecommerce.motomart.Models.Bike; // Updated import statement
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BikeRepository extends JpaRepository<Bike, Long> { // Renamed interface from ProductRepository to BikeRepository
    // Custom query methods can be added here
}
