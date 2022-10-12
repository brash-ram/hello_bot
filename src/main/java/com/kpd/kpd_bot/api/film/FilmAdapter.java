package com.kpd.kpd_bot.api.film;

import com.kpd.kpd_bot.api.Adapter;
import com.kpd.kpd_bot.api.film.model.Countries;
import com.kpd.kpd_bot.api.film.model.Genres;
import com.kpd.kpd_bot.api.film.model.PremiereResponseItem;
import com.kpd.kpd_bot.api.quote.model.BaseQuoteResponseDTO;
import com.kpd.kpd_bot.util.DateGetter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FilmAdapter implements Adapter {
    private final FilmAPI filmAPI;
    private final String ERROR_MESSAGE = "Кинопремьер не ожидается";

    @Override
    public String getTextFromMessageService(String... args) {
        PremiereResponseItem responseDTO;
        try {
            responseDTO = filmAPI.getFilm();
        } catch (RuntimeException ex) {
            return ERROR_MESSAGE;
        }
        return this.formatFromObjectToText(responseDTO);
    }

    private String formatFromObjectToText(PremiereResponseItem dto) {
        if (dto == null) {
            return ERROR_MESSAGE;
        }

        StringBuilder sb = new StringBuilder();
        List<Genres> genres = dto.getGenres();
        List<Countries> countries = dto.getCountries();

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

        for (Countries country : countries) {
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