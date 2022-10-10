package com.kpd.kpd_bot.api.film;

import com.kpd.kpd_bot.api.Adapter;
import com.kpd.kpd_bot.api.film.model.Countries;
import com.kpd.kpd_bot.api.film.model.Genres;
import com.kpd.kpd_bot.api.film.model.PremiereResponseItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class FilmAdapter implements Adapter {
    private final FilmAPI filmAPI;

    @Override
    public String getTextFromMessageService(String... args) {
        PremiereResponseItem responseDTO = filmAPI.getFilm();
        return this.formatFromObjectToText(responseDTO);
    }

    private String formatFromObjectToText(PremiereResponseItem dto) {
        StringBuilder sb = new StringBuilder();
        List<Genres> genres = dto.getGenres();
        List<Countries> countries = dto.getCountries();

        sb.append("Премьера этого месяца\n")
                .append("Фильм: ").append(dto.getNameRu()).append("\n")
                .append("Год: ").append(dto.getYear()).append("\n")
                .append("Продолжительность: ").append(dto.getDuration()).append(" мин\n")
                .append("Жанр: ");

        for (Genres genre : genres) {
            sb.append(genre.getGenre());
            if (genre != genres.get(genres.size()-1)) {
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

        String premiereRu = dto.getPremiereRu();
        LocalDate date = LocalDate.parse(premiereRu, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        sb.append("\nДата выхода: ")
                .append(date.format(DateTimeFormatter.ofPattern("dd MMMM yyyy", new Locale("ru"))));

        return sb.toString();
    }
}