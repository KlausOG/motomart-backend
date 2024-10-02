package com.ecommerce.motomart.Repositories;

import com.ecommerce.motomart.Models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    // Custom query methods can be added here
}
