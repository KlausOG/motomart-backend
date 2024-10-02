package com.ecommerce.motomart.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ShowroomNotFoundException extends RuntimeException {
    public ShowroomNotFoundException(Long id) {
        super("Showroom not found with ID: " + id);
    }
}
