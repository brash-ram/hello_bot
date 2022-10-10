package com.kpd.kpd_bot.api.film.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseFilmResponseDTO {
    private List<PremiereResponseItem> items;
}
