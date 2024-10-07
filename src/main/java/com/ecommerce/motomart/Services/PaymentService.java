package com.ecommerce.motomart.Services;

import com.ecommerce.motomart.DTO.PaymentDTO;
import com.ecommerce.motomart.Exceptions.PaymentNotFoundException;
import com.ecommerce.motomart.Models.Payment;
import com.ecommerce.motomart.Models.User;  // Import User model
import com.ecommerce.motomart.Repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public List<PaymentDTO> getAllPayments() {
        return paymentRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public PaymentDTO getPaymentById(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new PaymentNotFoundException(id));
        return convertToDTO(payment);
    }

    public PaymentDTO createPayment(PaymentDTO paymentDTO) {
        Payment payment = convertToEntity(paymentDTO);
        return convertToDTO(paymentRepository.save(payment));
    }

    public PaymentDTO updatePayment(Long id, PaymentDTO paymentDTO) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new PaymentNotFoundException(id));

        // Update payment fields
        payment.setAmount(paymentDTO.getAmount());
        payment.setPaymentDate(paymentDTO.getPaymentDate());

        // Uncomment and implement if needed
        // payment.setOrder(orderRepository.findById(paymentDTO.getOrderId())
        //         .orElseThrow(() -> new RuntimeException("Order not found")));

        return convertToDTO(paymentRepository.save(payment));
    }

    public void deletePayment(Long id) {
        if (!paymentRepository.existsById(id)) {
            throw new PaymentNotFoundException(id);
        }
        paymentRepository.deleteById(id);
    }

    // New method to process a payment
    public Payment processPayment(double amount, User user) {
        // Create a new Payment object
        Payment payment = new Payment();
        payment.setAmount(amount);
        payment.setPaymentDate(new Date());  // Set the payment date to the current date
        payment.setUser(user);               // Set the user who made the payment

        // Simulate a successful payment
        payment.setPaymentStatus("SUCCESS");
        payment.setPaymentMethod("Credit Card"); // Assuming the payment method is Credit Card

        // Save the payment to the repository
        return paymentRepository.save(payment);
    }

    private PaymentDTO convertToDTO(Payment payment) {
        return new PaymentDTO(
                payment.getPaymentId(),
                payment.getUser() != null ?payment.getUser().getUserId():null,
                payment.getOrder() != null ? payment.getOrder().getOrderId() : null,
                payment.getAmount(),
                payment.getPaymentDate(),
                payment.getPaymentStatus(), // Assuming you add this field to PaymentDTO
                payment.getPaymentMethod() // Assuming you add this field to PaymentDTO
        );
    }

    private Payment convertToEntity(PaymentDTO paymentDTO) {
        Payment payment = new Payment();
        payment.setAmount(paymentDTO.getAmount());
        payment.setPaymentDate(paymentDTO.getPaymentDate());
        // Uncomment if needed
        // payment.setOrder(orderRepository.findById(paymentDTO.getOrderId())
        //         .orElseThrow(() -> new RuntimeException("Order not found")));
        return payment;
    }
}
