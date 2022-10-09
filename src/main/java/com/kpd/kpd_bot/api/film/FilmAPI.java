package com.kpd.kpd_bot.api.film;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kpd.kpd_bot.api.film.model.BaseFilmResponseDTO;
import com.kpd.kpd_bot.config.FilmConfig;
import com.kpd.kpd_bot.service.WebService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class FilmAPI {
    private final FilmConfig filmConfig;
    private final WebService webService;
    private final ObjectMapper mapper;

    private String getUrl() {
        return UriComponentsBuilder.newInstance()
                .scheme("https").host(filmConfig.getUrl()).path("/{id}")
                .buildAndExpand("301").toUriString();
    }

    public BaseFilmResponseDTO getFilm() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-API-KEY", filmConfig.getToken());
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return  webService.<BaseFilmResponseDTO>makeGetRequest(this.getUrl(), BaseFilmResponseDTO.class, headers);
    }
}
