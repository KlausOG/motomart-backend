package com.ecommerce.motomart.Repositories;

import com.ecommerce.motomart.Models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // Custom query methods can be added here
}

