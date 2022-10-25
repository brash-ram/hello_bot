package com.kpd.kpd_bot.api.weather;

import com.kpd.kpd_bot.api.weather.model.Weather;
import com.kpd.kpd_bot.api.weather.model.WeatherMain;
import com.kpd.kpd_bot.api.weather.model.Wind;
import com.kpd.kpd_bot.api.weather.model.BaseWeatherResponseDTO;
import com.kpd.kpd_bot.api.Adapter;
import com.kpd.kpd_bot.util.PressureConverter;
import com.kpd.kpd_bot.util.WindDegreesConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service
@RequiredArgsConstructor
public class WeatherAdapter implements Adapter {

    private final WeatherAPI weatherAPI;
    private final String ERROR_MESSAGE = "\nК сожалению, в данный момент невозможно получить прогноз погоды.\n";
    private final String CITY_ERROR = "\nГород не найден.\n";
    @Override
    public Future<String> getTextFromMessageService(String... args) {
        String result = ERROR_MESSAGE;
        try {
            result = this.formatFromObjectToText(weatherAPI.getWeather(args[0]));
        }   catch (ArrayIndexOutOfBoundsException ex) {
            result = CITY_ERROR;
        }
        catch (RuntimeException ex) {
            ex.printStackTrace();
        }
        return new AsyncResult<>(result);
    }

    private String formatFromObjectToText(BaseWeatherResponseDTO dto) {
        if (dto == null) return ERROR_MESSAGE;
        StringBuilder sb = new StringBuilder();
        Weather weather = dto.getWeather().get(0);
        WeatherMain weatherMain = dto.getMain();
        Wind wind = dto.getWind();
        sb.append("\n*Погода в ")
                .append(dto.getName()).append(" сейчас*\n");

        if ((int)weatherMain.getTemp() > 0) {
            sb.append("+");
        }

        sb.append((int)weatherMain.getTemp()).append("°, ")
                .append(weather.getDescription()).append("\n")
                .append("Ощущается как ");

        if ((int)weatherMain.getFeels_like() > 0) {
            sb.append("+");
        }

        sb.append((int)weatherMain.getFeels_like()).append("°\n")
                .append("Давление: ").append(PressureConverter.convertPressure(weatherMain.getPressure()))
                .append(" мм рт. ст.\n").append("Влажность: ").append(weatherMain.getHumidity()).append(" %\n")
                .append("Ветер: ").append((int)wind.getSpeed()).append(" м/c, ")
                .append(WindDegreesConverter.convertWindDegreesToDirection(wind.getDeg())).append("\n");
        return sb.toString();
    }
}
