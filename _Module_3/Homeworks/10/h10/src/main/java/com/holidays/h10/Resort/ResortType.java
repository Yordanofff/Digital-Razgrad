package com.holidays.h10.Resort;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "types")
@Getter
@Setter
@NoArgsConstructor
public class ResortType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;

    public ResortType(String name) {
        this.name = name;
    }
}
