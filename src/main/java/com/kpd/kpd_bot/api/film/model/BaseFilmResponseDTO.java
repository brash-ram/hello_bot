package com.kpd.kpd_bot.api.film.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseFilmResponseDTO {
    private long kinopoiskId;
    private String nameRu;
    private double ratingKinopoisk;
    private int year;
    private int filmLength;
    private String shortDescription;
    private List<Countries> countries;
    private List<Genres> genres;
}
