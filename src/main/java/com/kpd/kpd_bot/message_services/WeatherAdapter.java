package com.kpd.kpd_bot.message_services;

import com.kpd.kpd_bot.api.WeatherAPI;

public class WeatherAdapter implements Adapter{
    @Override
    public String getTextFromMessageService() {
        return new WeatherAPI().testMethod();
    }
}
