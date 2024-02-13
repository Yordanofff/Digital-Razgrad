package com.holidays.h10.Resort;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "resorts")
@Getter
@Setter
@NoArgsConstructor
public class Resort {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @ManyToMany
    private List<ResortType> resortTypes;

    public Resort(String name, Country country) {
        this.name = name;
        this.country = country;
    }
}
