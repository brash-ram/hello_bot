package com.kpd.kpd_bot.dto.response;

import com.kpd.kpd_bot.api.weather.model.Weather;
import com.kpd.kpd_bot.api.weather.model.WeatherMain;
import com.kpd.kpd_bot.api.weather.model.Wind;
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