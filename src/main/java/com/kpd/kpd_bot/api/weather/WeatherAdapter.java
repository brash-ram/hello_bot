package com.kpd.kpd_bot.api.weather;

import com.kpd.kpd_bot.entity.cache.weather.Weather;
import com.kpd.kpd_bot.entity.cache.weather.WeatherMain;
import com.kpd.kpd_bot.entity.cache.weather.Wind;
import com.kpd.kpd_bot.entity.cache.weather.BaseWeather;
import com.kpd.kpd_bot.api.Adapter;
import com.kpd.kpd_bot.utils.PressureConverter;
import com.kpd.kpd_bot.utils.WindDegreesConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service
@RequiredArgsConstructor
public class WeatherAdapter implements Adapter {

    private final WeatherService weatherService;
    private final String ERROR_MESSAGE = "\nК сожалению, в данный момент невозможно получить прогноз погоды.\n";
    private final String CITY_ERROR = "\nГород не найден.\n";
    private final String WEATHER = "\n*Погода в ";
    private final String NOW = " сейчас*\n";
    private final String FEELS_LIKE = "Ощущается как ";
    private final String PRESSURE = "Давление: ";
    private final String PRESSURE_UNIT = " мм рт. ст.\n";
    private final String HUMIDITY = "Влажность: ";
    private final String TEMP_UNIT = "°";
    private final String HUMIDITY_UNIT = " %";
    private final String WIND = "Ветер: ";
    private final String WIND_SPEED_UNIT = " м/c, ";
    private final String PLUS = "+";



    @Override
    public Future<String> getTextFromMessageService(String... args) {
        String result = ERROR_MESSAGE;

        try {
            result = this.formatFromObjectToText(weatherService.getWeather(args[0], args[1]));
        }   catch (RuntimeException ex) {
            ex.printStackTrace();
        }

        return new AsyncResult<>(result);
    }

    private String formatFromObjectToText(BaseWeather dto) {
        if (dto == null) {
            return CITY_ERROR;
        }

        Weather weather = dto.getWeather().get(0);
        WeatherMain weatherMain = dto.getMain();
        Wind wind = dto.getWind();

        StringBuilder sb = new StringBuilder()
                .append(WEATHER)
                .append(dto.getName())
                .append(NOW);

        addPlusSymbol(sb, weatherMain.getTemp());

        sb.append((int)weatherMain.getTemp())
                .append(TEMP_UNIT)
                .append(", ")
                .append(weather.getDescription())
                .append("\n")
                .append(FEELS_LIKE);

        addPlusSymbol(sb, weatherMain.getFeels_like());

        sb.append((int)weatherMain.getFeels_like())
                .append(TEMP_UNIT)
                .append("\n")
                .append(PRESSURE)
                .append(PressureConverter.convertPressure(weatherMain.getPressure()))
                .append(PRESSURE_UNIT)
                .append(HUMIDITY)
                .append(weatherMain.getHumidity())
                .append(HUMIDITY_UNIT)
                .append("\n")
                .append(WIND)
                .append((int)wind.getSpeed())
                .append(WIND_SPEED_UNIT)
                .append(WindDegreesConverter.convertWindDegreesToDirection(wind.getDeg()))
                .append("\n");

        return sb.toString();
    }

    private void addPlusSymbol(StringBuilder sb, double temp) {
        if ((int)temp > 0) {
            sb.append(PLUS);
        }
    }

}
