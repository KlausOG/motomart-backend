package com.ecommerce.motomart.Repositories;

import com.ecommerce.motomart.Models.Showroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowroomRepository extends JpaRepository<Showroom, Long> {
    // Custom query methods can be added here
}

