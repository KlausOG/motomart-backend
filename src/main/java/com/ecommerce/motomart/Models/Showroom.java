package com.ecommerce.motomart.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "showrooms")
public class Showroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long showroomId;

    private String name;
    private String location;

    @OneToMany(mappedBy = "showroom")
    private List<Product> products;

    // Getters and Setters
}
