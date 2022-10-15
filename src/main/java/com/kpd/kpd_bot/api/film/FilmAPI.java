package com.kpd.kpd_bot.api.film;

import com.kpd.kpd_bot.api.film.model.BaseFilmResponseDTO;
import com.kpd.kpd_bot.entity.cache.Film;
import com.kpd.kpd_bot.config.FilmConfig;
import com.kpd.kpd_bot.service.WebService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import java.time.LocalDate;
import java.util.Random;


@Service
@RequiredArgsConstructor
public class FilmAPI {
    private final FilmConfig filmConfig;
    private final WebService webService;

    private String getUrl() {
        return UriComponentsBuilder.newInstance()
                .scheme("https").host(filmConfig.getUrl()).path("?year={year}&month={month}")
                .buildAndExpand(LocalDate.now().getYear(), String.valueOf(LocalDate.now().getMonth()))
                .toUriString();
    }

    public Film getFilm() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-API-KEY", filmConfig.getToken());
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        BaseFilmResponseDTO responseDTO = webService.<BaseFilmResponseDTO>makeGetRequest(this.getUrl(), BaseFilmResponseDTO.class, headers);
        return getCurrentYearFilm(responseDTO);
    }

    private Film getCurrentYearFilm(BaseFilmResponseDTO dto) {
        Film film = dto.getItems()
                .stream()
                .filter(item -> item.getYear() == LocalDate.now().getYear())
                .skip(new Random().nextInt(dto.getItems().size()-1))
                .findFirst()
                .get();
        return film;
    }
}
