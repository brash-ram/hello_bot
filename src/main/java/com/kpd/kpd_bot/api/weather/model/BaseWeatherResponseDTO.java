package com.kpd.kpd_bot.api.weather.model;

import lombok.*;
import java.util.List;

@Data
@AllArgsConstructor
public class BaseWeatherResponseDTO {
    private List<Weather> weather;
    private WeatherMain main;
    private Wind wind;
    private String name;
}