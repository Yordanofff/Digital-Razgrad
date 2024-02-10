package com.example.Task9.Clothes;

import com.example.Task9.Brand.Brand;
import com.example.Task9.Clothes.Size.ClothesSize;
import com.example.Task9.Clothes.Type.ClothType;
import jakarta.persistence.*;

@Entity
public class Clothes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "clothes_size_id")
    private ClothesSize size;

    @ManyToOne
    @JoinColumn(name = "cloth_type_id")
    private ClothType type;

    private Double price;

    public Clothes() {
    }

    public Clothes(String name, Brand brand, ClothesSize size, ClothType type, Double price, int quantity) {
        this.name = name;
        this.brand = brand;
        this.size = size;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public ClothType getType() {
        return type;
    }

    public void setType(ClothType type) {
        this.type = type;
    }

    public ClothesSize getSize() {
        return size;
    }

    public void setSize(ClothesSize size) {
        this.size = size;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
