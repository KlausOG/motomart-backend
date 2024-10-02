package com.ecommerce.motomart.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Long id;
    private Long userId; // User placing the order
    private List<Long> productIds; // List of product IDs in the order
    private BigDecimal totalAmount;
    private Date orderDate;

    // Getters and Setters
}
