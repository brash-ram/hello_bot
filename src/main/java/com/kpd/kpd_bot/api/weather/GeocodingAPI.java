package com.kpd.kpd_bot.api.weather;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kpd.kpd_bot.api.weather.model.GeoCoordinate;
import com.kpd.kpd_bot.api.weather.model.GeoCoordinateResponseDTO;
import com.kpd.kpd_bot.config.WeatherConfig;
import com.kpd.kpd_bot.service.WebService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GeocodingAPI {

    private final WebService webService;

    private final WeatherConfig weatherConfig;
    private final ObjectMapper mapper;

    private String getUrl(String city) {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme("https").host(weatherConfig.getUrl()).path("?q={city name}&limit=1&appid={API key}")
                .buildAndExpand(city, weatherConfig.getToken());
        return uriComponents.toUriString();
    }

    public GeoCoordinateResponseDTO getGeoCoordinate(String city) {
        Object responseApi = webService.<Object>makeRequest(this.getUrl(city), Object.class);
        return mapper.convertValue(responseApi, GeoCoordinateResponseDTO.class);
    }
}
