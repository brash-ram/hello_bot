package com.kpd.kpd_bot.util;

public class PressureConverter {
    private PressureConverter() {}

    public static int convertPressure(int pressure) {
        int pressureInMercuryMillimeters = (int)(0.750064 * pressure);
        return pressureInMercuryMillimeters;
    }

}
