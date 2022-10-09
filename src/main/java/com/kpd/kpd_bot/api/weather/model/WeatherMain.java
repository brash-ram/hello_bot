package com.kpd.kpd_bot.api.weather.model;

import lombok.Data;

@Data
public class WeatherMain {
    private double temp;
    private double feels_like;
    private int pressure;
    private int humidity;
}
