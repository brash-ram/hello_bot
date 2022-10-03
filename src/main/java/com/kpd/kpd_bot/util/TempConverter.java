package com.kpd.kpd_bot.util;

public class TempConverter {
    private TempConverter() {};

    private final static double DIFFERENCE = 273.15;

    public static long convertTempFromKelvinToCelsius(double temp) {
        return Math.round(temp-DIFFERENCE);
    }
}
