package com.ecommerce.motomart.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AccessoryNotFoundException extends RuntimeException {
    public AccessoryNotFoundException(Long id) {
        super("Accessory not found with ID: " + id);
    }
}
