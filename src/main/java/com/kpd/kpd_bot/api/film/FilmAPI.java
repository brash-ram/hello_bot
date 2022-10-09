package com.kpd.kpd_bot.api.film;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kpd.kpd_bot.api.film.model.BaseFilmResponseDTO;
import com.kpd.kpd_bot.config.FilmConfig;
import com.kpd.kpd_bot.service.WebService;
import lombok.RequiredArgsConstructor;
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
                .scheme("https").host(filmConfig.getUrl()).path("/api/v2.2/films/{id}{method: 'GET'," +
                        "headers: {'X-API-KEY': {API key}, 'Content-Type': 'application/json'}}")
                .buildAndExpand("301", filmConfig.getToken()).toUriString();
    }

    public BaseFilmResponseDTO getFilm() {
        Object responseApi = webService.<Object>makePostRequest(this.getUrl(), Object.class);
        return mapper.convertValue(responseApi, BaseFilmResponseDTO.class);
    }
}
