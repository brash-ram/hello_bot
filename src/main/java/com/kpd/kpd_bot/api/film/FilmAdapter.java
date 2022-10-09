package com.kpd.kpd_bot.api.film;

import com.kpd.kpd_bot.api.Adapter;
import com.kpd.kpd_bot.api.film.model.BaseFilmResponseDTO;
import com.kpd.kpd_bot.api.film.model.Countries;
import com.kpd.kpd_bot.api.film.model.Genres;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FilmAdapter implements Adapter {
    private final FilmAPI filmAPI;

    @Override
    public String getTextFromMessageService(String... args) {
        BaseFilmResponseDTO responseDTO = filmAPI.getFilm();
        return this.formatFromObjectToText(responseDTO);
    }

    private String formatFromObjectToText(BaseFilmResponseDTO dto) {
        StringBuilder sb = new StringBuilder();
        List<Genres> genres = dto.getGenres();
        List<Countries> countries = dto.getCountries();
        sb.append("\nФильм дня:\n")
                .append("Фильм: ").append(dto.getNameRu()).append("\n")
                .append("Рейтинг на Кинопоиске: ").append(dto.getRatingKinopoisk()).append("\n")
                .append("Год: ").append(dto.getYear()).append("\n")
                .append("Продолжительность: ").append(dto.getFilmLength()).append(" мин\n")
                .append("Жанр: ").append(genres).append("\n")
                .append("Страна: ").append(countries).append("\n");
        return sb.toString();
    }
}