package com.kpd.kpd_bot.api.weather;

import com.kpd.kpd_bot.api.weather.WeatherAPI;
import com.kpd.kpd_bot.api.weather.model.Weather;
import com.kpd.kpd_bot.api.weather.model.WeatherMain;
import com.kpd.kpd_bot.api.weather.model.Wind;
import com.kpd.kpd_bot.api.weather.model.BaseWeatherResponseDTO;
import com.kpd.kpd_bot.api.Adapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeatherAdapter implements Adapter {

    private final WeatherAPI weatherAPI;
    @Override
    public String getTextFromMessageService() {
        BaseWeatherResponseDTO responseDTO = weatherAPI.getWeather();
        return this.formatFromObjectToText(responseDTO);
    }

    private String formatFromObjectToText(BaseWeatherResponseDTO dto) {
        StringBuilder sb = new StringBuilder();
        Weather weather = dto.getWeather().get(0);
        WeatherMain weatherMain = dto.getMain();
        Wind wind = dto.getWind();
        sb.append("Погода в ")
                .append(dto.getName()).append(":\n")
                .append(weather.getMain()).append("\n")
                .append("Температура: ").append(weatherMain.getTemp()).append(" °C\n")
                .append("Ощущается как: ").append(weatherMain.getFeels_like()).append(" °C\n")
                .append("Давление: ").append(weatherMain.getPressure()).append("\n")
                .append(weatherMain.getHumidity()).append("\n")
                .append(wind.getSpeed()).append("\n")
                .append(wind.getDeg()).append("\n")
                .append(wind.getGust()).append("\n");
        return sb.toString();
    }
}
