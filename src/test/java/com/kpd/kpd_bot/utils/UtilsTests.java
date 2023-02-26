package com.kpd.kpd_bot.utils;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UtilsTests {

    @ParameterizedTest
    @CsvSource({"0,С↑", "130,ЮВ↘", "250,З←"})
    public void convertWindDegreesToDirectionTest(String input, String expected) {
        String actualValue = WindDegreesConverter.convertWindDegreesToDirection(Integer.parseInt(input));
        assertEquals(expected, actualValue);
    }

    @ParameterizedTest
    @CsvSource({"-3hour,7:00", "+hour,11:00", "-min,9:59", "+10min,10:10"})
    public void getNewTimeTest(String input, String expected) {
        String time = "10:00";
        String actualValue = TimeSendInlineKeyboardHandler.getNewTime(time, input);
        assertEquals(expected, actualValue);
    }
}
