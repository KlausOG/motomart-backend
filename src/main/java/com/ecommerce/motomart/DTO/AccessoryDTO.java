package com.ecommerce.motomart.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccessoryDTO {
    private Long accessoryId; // ID of the accessory

    @NotBlank(message = "Name is mandatory")
    private String name; // Accessory name

    private String description; // Accessory description

    @NotNull(message = "Bike IDs cannot be null")
    private List<Long> bikeIds; // List of associated bike IDs

    @NotNull(message = "Category is mandatory")
    private String category; // Category of the accessory

    @NotNull(message = "Brand ID is mandatory")
    private Long brandId; // ID of the associated brand

    private String imageUrl; // URL or path for the accessory image

    @NotNull(message = "Price is mandatory")
    private Double price; // Price of the accessory

    private Date createdOn; // Date when the accessory was created

    private Long visitCount; // Visit count for tracking popularity
}
