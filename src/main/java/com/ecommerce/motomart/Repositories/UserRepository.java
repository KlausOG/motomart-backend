package com.ecommerce.motomart.Repositories;


import com.ecommerce.motomart.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // You can add custom query methods if needed
}
