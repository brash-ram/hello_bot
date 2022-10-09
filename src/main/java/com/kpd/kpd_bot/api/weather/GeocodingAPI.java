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

    private String getUrl(String city) {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme("http").host(weatherConfig.getGeoUrl()).path("direct?q={city name},{country}&limit=1&appid={API key}")
                .buildAndExpand(city, "643", weatherConfig.getToken());
        return uriComponents.toUriString();
    }

    public GeoCoordinate getGeoCoordinate(String city) {
        return webService.<GeoCoordinate[]>makeGetRequest(this.getUrl(city), GeoCoordinate[].class)[0];
    }
}
