package com.kpd.kpd_bot.api.weather;

import com.kpd.kpd_bot.api.weather.model.Weather;
import com.kpd.kpd_bot.api.weather.model.WeatherMain;
import com.kpd.kpd_bot.api.weather.model.Wind;
import com.kpd.kpd_bot.api.weather.model.BaseWeatherResponseDTO;
import com.kpd.kpd_bot.api.Adapter;
import com.kpd.kpd_bot.util.PressureConverter;
import com.kpd.kpd_bot.util.WindDegreesConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeatherAdapter implements Adapter {

    private final WeatherAPI weatherAPI;
    private final String ERROR_MESSAGE = "Сегодня хорошая погода)";
    @Override
    public String getTextFromMessageService(String... args) {
        BaseWeatherResponseDTO responseDTO;
        try {
            responseDTO = weatherAPI.getWeather(args[0]);
        } catch (RuntimeException ex) {
            return ERROR_MESSAGE;
        }
        return this.formatFromObjectToText(responseDTO);
    }

    private String formatFromObjectToText(BaseWeatherResponseDTO dto) {
        if (dto == null) return ERROR_MESSAGE;
        StringBuilder sb = new StringBuilder();
        Weather weather = dto.getWeather().get(0);
        WeatherMain weatherMain = dto.getMain();
        Wind wind = dto.getWind();
        sb.append("\n*Погода в ")
                .append(dto.getName()).append(" сейчас*\n")
                .append("Температура ").append((int)weatherMain.getTemp()).append(" °C, ")
                .append(weather.getDescription()).append("\n")
                .append("Ощущается как ").append((int)weatherMain.getFeels_like()).append(" °C\n")
                .append("Давление: ").append(PressureConverter.convertPressure(weatherMain.getPressure()))
                .append(" мм рт. ст.\n").append("Влажность: ").append(weatherMain.getHumidity()).append(" %\n")
                .append("Ветер: ").append((int)wind.getSpeed()).append(" м/c, ")
                .append(WindDegreesConverter.convertWindDegreesToDirection(wind.getDeg())).append("\n");
        return sb.toString();
    }
}
