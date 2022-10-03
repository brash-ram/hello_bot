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

    private String getUrl() {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme("https").host(weatherConfig.getUrl()).path("?lat={lat}&lon={lon}&appid={API key}")
                .buildAndExpand("54.62", "39.69", weatherConfig.getToken());
        return uriComponents.toUriString();
    }

    public BaseWeatherResponseDTO getWeather() {
        Object responseApi = webService.<Object>makeRequest(this.getUrl(), Object.class);
        return mapper.convertValue(responseApi, BaseWeatherResponseDTO.class);
    }
}
