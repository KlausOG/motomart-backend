package com.ecommerce.motomart.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BikeNotFoundException extends RuntimeException { // Renamed from ProductNotFoundException to BikeNotFoundException
    public BikeNotFoundException(Long id) {
        super("Bike not found with ID: " + id); // Updated message to refer to Bike
    }
}
