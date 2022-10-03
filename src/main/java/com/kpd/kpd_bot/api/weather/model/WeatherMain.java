package com.kpd.kpd_bot.api.weather.model;

import com.kpd.kpd_bot.util.TempConverter;
import lombok.Data;

@Data
public class WeatherMain {
    private double temp;
    private double feels_like;

    private int pressure;

    private int humidity;

    public double getTemp() {
        return TempConverter.convertTempFromKelvinToCelsius(this.temp);
    }

    public long getFeels_like() {
        return TempConverter.convertTempFromKelvinToCelsius(this.feels_like);
    }
}
