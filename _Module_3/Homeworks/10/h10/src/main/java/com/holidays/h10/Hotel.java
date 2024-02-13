package com.holidays.h10;

import com.holidays.h10.Resort.Resort;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "hotels")
@Getter
@Setter
@NoArgsConstructor
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "resort_id")
    private Resort resort;
    private String name;
    private int roomCount;
    private int numberOfStars;
    private boolean hasSpa;

    public Hotel(Resort resort, String name, int roomCount, int numberOfStars, boolean hasSpa) {
        this.resort = resort;
        this.name = name;
        this.roomCount = roomCount;
        this.numberOfStars = numberOfStars;
        this.hasSpa = hasSpa;
    }

}
