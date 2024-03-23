package com.h19.h19.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "geolocation_id")
    private Geolocation geolocation;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    private String street;

    @Column(name = "number")
    private String streetNumber;

    private String zipcode;
}
