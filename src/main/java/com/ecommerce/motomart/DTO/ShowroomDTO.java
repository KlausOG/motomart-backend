package com.ecommerce.motomart.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShowroomDTO {
    private Long id;

    @NotBlank
    private String name;

    private String location;

    // Getters and Setters
}
