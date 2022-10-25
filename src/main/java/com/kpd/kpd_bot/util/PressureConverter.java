package com.kpd.kpd_bot.util;

public class PressureConverter {
    private PressureConverter() {}

    public static int convertPressure(int pressure) {
        return (int)(0.750064 * pressure);
    }

}
