package com.kpd.kpd_bot.dto.response;

import lombok.*;

@Data
@AllArgsConstructor
public class WeatherResponseDTO {
    private String city;
    private double temperature;
    private double humidity;
    private double windSpeed;
    private String icon;
    private String description;

}