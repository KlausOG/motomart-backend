package com.ecommerce.motomart.Controllers;

import com.ecommerce.motomart.Exceptions.PaymentNotFoundException;
import com.ecommerce.motomart.Models.Payment;
import com.ecommerce.motomart.Repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentRepository paymentRepository;

    @GetMapping
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
        return paymentRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new PaymentNotFoundException(id));
    }

    @PostMapping
    public Payment createPayment(@RequestBody Payment payment) {
        return paymentRepository.save(payment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Payment> updatePayment(@PathVariable Long id, @RequestBody Payment paymentDetails) {
        return paymentRepository.findById(id)
                .map(payment -> {
                    // Update payment details if needed
                    return ResponseEntity.ok(paymentRepository.save(payment));
                })
                .orElseThrow(() -> new PaymentNotFoundException(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
        if (!paymentRepository.existsById(id)) {
            throw new PaymentNotFoundException(id);
        }
        paymentRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
