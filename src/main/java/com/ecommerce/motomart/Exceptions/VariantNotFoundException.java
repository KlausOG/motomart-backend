package com.ecommerce.motomart.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class VariantNotFoundException extends RuntimeException {
    public VariantNotFoundException(Long id) {
        super("Variant not found with ID: " + id);
    }
}
