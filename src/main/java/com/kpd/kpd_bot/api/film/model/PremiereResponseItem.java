package com.kpd.kpd_bot.api.film.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PremiereResponseItem {
    private String nameRu;
    private int year;
    private int duration;
    private List<Countries> countries;
    private List<Genres> genres;
    private String premiereRu;
}
