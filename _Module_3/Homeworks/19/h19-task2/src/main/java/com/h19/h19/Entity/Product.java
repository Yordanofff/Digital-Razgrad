package com.h19.h19.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String title;
    private Double price;
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
