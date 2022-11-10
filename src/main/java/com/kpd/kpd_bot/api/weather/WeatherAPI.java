package com.kpd.kpd_bot.api.weather;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kpd.kpd_bot.api.weather.model.GeoCoordinate;
import com.kpd.kpd_bot.service.WebService;
import com.kpd.kpd_bot.config.WeatherConfig;
import com.kpd.kpd_bot.entity.cache.weather.BaseWeather;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RequiredArgsConstructor
@Service
public class WeatherAPI {
    private final WebService webService;

    private final WeatherConfig weatherConfig;

    private final ObjectMapper mapper;
    private final GeocodingAPI geocodingAPI;

    private String getUrl(Double lat, Double lon) {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host(weatherConfig.getUrl())
                .path("?lat={lat}&lon={lon}&exclude=current&units=metric&lang=ru&appid={API key}")
                .buildAndExpand(lat.toString(), lon.toString(), weatherConfig.getToken());

        return uriComponents.toUriString();
    }

    public BaseWeather getWeather(String city) {
        GeoCoordinate coordinate = null;

        try {
            coordinate = geocodingAPI.getGeoCoordinate(city);
        } catch (ArrayIndexOutOfBoundsException ex) {
            ex.printStackTrace();
            throw new ArrayIndexOutOfBoundsException();

        } catch (RuntimeException ex) {
            ex.printStackTrace();
        }
        return webService.<BaseWeather>makePostRequest(this.getUrl(coordinate.getLat(), coordinate.getLon()), BaseWeather.class);
    }
}
