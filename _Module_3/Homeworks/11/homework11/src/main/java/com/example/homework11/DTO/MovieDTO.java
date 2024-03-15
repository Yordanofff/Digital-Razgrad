package com.example.homework11.DTO;

import com.example.homework11.Entities.Actor;
import com.example.homework11.Entities.Genre;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class MovieDTO {
    @Size(min=1, max=30, message = "Name should be between 1 and 30 characters")
    @Column(nullable = false)  // no need to be unique
    private String name;

    @Size(min=1, max=30, message = "Name should be between 1 and 30 characters")
    @Column(nullable = false)  // no need to be unique
    private String confirmedName;

    @Column(nullable = false)
    private List<Genre> genreList;

    @Min(1900)
    @Max(value = 2100)
    @Column(nullable = false)
    private int year;

    @Column(nullable = false)
    private List<Actor> actorList;

    private boolean isLeapYear;
}
