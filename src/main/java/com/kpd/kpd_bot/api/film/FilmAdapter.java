package com.kpd.kpd_bot.api.film;

import com.kpd.kpd_bot.api.Adapter;
import com.kpd.kpd_bot.api.film.model.Country;
import com.kpd.kpd_bot.api.film.model.Genres;
import com.kpd.kpd_bot.entity.cache.Film;
import com.kpd.kpd_bot.utils.DateGetter;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

@Service
@RequiredArgsConstructor
public class FilmAdapter implements Adapter {
    private final FilmService filmService;
    private final String ERROR_MESSAGE = "\nК сожалению, в данный момент невозможно получить " +
            "кинопремьеру этого месяца.\n";
    private final String MOVIE_PREMIERE = "\n*Кинопремьера этого месяца*\n";
    private final String FILM = "Фильм: \"";
    private final String DURATION = "Продолжительность: ";
    private final String MINUTES = " мин\n";
    private final String GENRE = "Жанр: ";
    private final String COUNTRY = "\nСтрана: ";
    private final String PREMIERE_DATE = "\nДата выхода: ";

    @Override
    public Future<String> getTextFromMessageService(String... args) {
        String result = ERROR_MESSAGE;

        try {
            result = this.formatFromObjectToText(filmService.getFilm());
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

        return new AsyncResult<>(result);
    }

    private String formatFromObjectToText(Film dto) {
        List<Genres> genres = new ArrayList<>(dto.getGenres());
        List<Country> countries = new ArrayList<>(dto.getCountries());

        StringBuilder sb = new StringBuilder().append(MOVIE_PREMIERE)
                .append(FILM)
                .append(dto.getNameRu())
                .append("\"\n")
                .append(DURATION)
                .append(dto.getDuration())
                .append(MINUTES)
                .append(GENRE);

        prettyGenresPrint(sb, genres);
        sb.append(COUNTRY);
        prettyCountriesPrint(sb, countries);
        sb.append(PREMIERE_DATE)
                .append(DateGetter.getFormattedDate(dto.getPremiereRu(), "yyyy-MM-dd"));

        return sb.toString();
    }

    private void prettyGenresPrint(StringBuilder sb, List<Genres> genres) {
        for (Genres genre : genres) {
            sb.append(genre.getGenre());
            if (genre != genres.get(genres.size() - 1)) {
                sb.append(", ");
            }
        }
    }

    private void prettyCountriesPrint(StringBuilder sb, List<Country> countries) {
        for (Country country : countries) {
            sb.append(country.getCountry());
            if (country != countries.get(countries.size()-1)) {
                sb.append(", ");
            }
        }
    }

}