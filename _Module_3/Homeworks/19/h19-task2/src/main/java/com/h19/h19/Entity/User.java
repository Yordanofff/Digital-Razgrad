package com.h19.h19.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String email;
    private String username;
    private String password;

    @ManyToOne
    @JoinColumn(name = "name_id")
    private Name name;

    private String phone;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;
}
