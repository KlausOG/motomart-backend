package com.ecommerce.motomart.Repositories;

import com.ecommerce.motomart.Models.BikeCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BikeCategoryRepository extends JpaRepository<BikeCategory, Long> {
}
