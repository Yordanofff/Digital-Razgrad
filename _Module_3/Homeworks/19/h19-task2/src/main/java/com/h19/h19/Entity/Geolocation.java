package com.h19.h19.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "geolocation")
@Data
public class Geolocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "lat")
    private Double latitude;

    @Column(name = "lon")
    private Double longitude;
}
