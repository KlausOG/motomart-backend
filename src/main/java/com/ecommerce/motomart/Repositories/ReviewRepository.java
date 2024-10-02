package com.ecommerce.motomart.Repositories;

import com.ecommerce.motomart.Models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    // Custom query methods can be added here
}

