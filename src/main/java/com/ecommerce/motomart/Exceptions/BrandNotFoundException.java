package com.ecommerce.motomart.Exceptions;

public class BrandNotFoundException extends RuntimeException {
    public BrandNotFoundException(Long id) {
        super("Brand not found with ID: " + id);
    }
}
