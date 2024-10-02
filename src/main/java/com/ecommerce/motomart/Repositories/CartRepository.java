package com.ecommerce.motomart.Repositories;

import com.ecommerce.motomart.Models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    // Custom query methods can be added here
}
