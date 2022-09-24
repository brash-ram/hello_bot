package com.kpd.kpd_bot.api.weather;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kpd.kpd_bot.service.WebService;
import com.kpd.kpd_bot.config.WeatherConfig;
import com.kpd.kpd_bot.api.weather.model.BaseWeatherResponseDTO;
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


    public String testMethod(){
        return "Погода";
    }

    private String getUri() {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .path("?lat={lat}&lon={lon}&appid={API key}")
                .buildAndExpand("44.34", "10.99", weatherConfig.getToken());
        return uriComponents.toUriString();
    }

    public BaseWeatherResponseDTO getWeather() {
        Object responseApi = webService.<Object>makeRequest(weatherConfig.getUrl(), this.getUri(), Object.class);
        return mapper.convertValue(responseApi, BaseWeatherResponseDTO.class);
    }
}
