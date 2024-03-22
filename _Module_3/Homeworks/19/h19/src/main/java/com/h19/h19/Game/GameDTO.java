package com.h19.h19.Game;

import com.h19.h19.Company.Company;
import com.h19.h19.Genre.Genre;
import lombok.Data;

@Data
public class GameDTO {
    private Long id;
    private String name;
    private int yearReleased;
    private Genre genre;
    private Company company;
}
