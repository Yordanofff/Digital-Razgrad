package com.h19.h19.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "names")
@Data
public class Name {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String firstName;
    private String lastname;
}
