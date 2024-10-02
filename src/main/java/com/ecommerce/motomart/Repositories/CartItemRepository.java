package com.ecommerce.motomart.Repositories;

import com.ecommerce.motomart.Models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    // Custom query methods can be added here
}

