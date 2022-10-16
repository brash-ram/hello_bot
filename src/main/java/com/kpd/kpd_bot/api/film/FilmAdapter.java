package com.kpd.kpd_bot.api.film;

import com.kpd.kpd_bot.api.Adapter;
import com.kpd.kpd_bot.api.film.model.Country;
import com.kpd.kpd_bot.api.film.model.Genres;
import com.kpd.kpd_bot.entity.cache.Film;
import com.kpd.kpd_bot.util.DateGetter;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Future;

@Service
@RequiredArgsConstructor
public class FilmAdapter implements Adapter {
    private final FilmService filmService;
    private final String ERROR_MESSAGE = "Кинопремьер не ожидается";

    @Override
    public Future<String> getTextFromMessageService(String... args) {
        String result = ERROR_MESSAGE;
        try {
            result = this.formatFromObjectToText(filmService.getFilm());
        } catch (RuntimeException ex) {
            ex.printStackTrace();
        }
        return new AsyncResult<>(result);
    }

    private String formatFromObjectToText(Film dto) {
        StringBuilder sb = new StringBuilder();
        List<Genres> genres = new ArrayList<>(dto.getGenres());
        List<Country> countries = new ArrayList<>(dto.getCountries());

        sb.append("*Кинопремьера этого месяца*\n")
                .append("Фильм: \"").append(dto.getNameRu()).append("\"\n")
                .append("Продолжительность: ").append(dto.getDuration()).append(" мин\n")
                .append("Жанр: ");

        for (Genres genre : genres) {
            sb.append(genre.getGenre());
            if (genre != genres.get(genres.size() - 1)) {
                sb.append(", ");
            }
        }

        sb.append("\nСтрана: ");

        for (Country country : countries) {
            sb.append(country.getCountry());
            if (country != countries.get(countries.size()-1)) {
                sb.append(", ");
            }
        }

        sb.append("\nДата выхода: ")
                .append(DateGetter.getFormattedDate(dto.getPremiereRu(), "yyyy-MM-dd"));

        return sb.toString();
    }
}