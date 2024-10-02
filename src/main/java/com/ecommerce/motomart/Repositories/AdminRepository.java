package com.ecommerce.motomart.Repositories;


import com.ecommerce.motomart.Models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    // Custom query methods can be added here
}
