package com.kpd.kpd_bot.utils;

import com.kpd.kpd_bot.entity.ExchangeRatesSetting;
import com.kpd.kpd_bot.entity.UserSetting;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import static org.junit.jupiter.api.Assertions.*;

public class UtilsTests {
    private static final String TEST_STRING = "TEST";

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

    @Test
    public void createBasicSettingKeyboardTest() {
        InlineKeyboardMarkup keyboardMarkup = SettingKeyboard.createBasicSettingKeyboard(new UserSetting().setSendMainMessage(true));
        assertEquals(keyboardMarkup.getKeyboard().size(), 4);
        assertTrue(keyboardMarkup.getKeyboard().get(3).get(0).getText().contains(SettingKeyboard.yes));
    }

    @Test
    public void createInlineKeyboardExchangeRatesSettingTest() {
        InlineKeyboardMarkup keyboardMarkup = SettingKeyboard
                .createInlineKeyboardExchangeRatesSetting(new ExchangeRatesSetting(null, true, false, true, true, true, true));
        assertTrue(keyboardMarkup.getKeyboard().get(1).get(0).getText().contains(SettingKeyboard.yes));
    }

    @Test
    public void addInlineButtonInRowSizeTest() {
        InlineKeyboardConstructor inlineKeyboardConstructor = new InlineKeyboardConstructor();
        inlineKeyboardConstructor.addInlineButtonInRow(TEST_STRING, TEST_STRING);
        inlineKeyboardConstructor.addNewInlineRow();
        assertEquals(inlineKeyboardConstructor.size(), 1);
    }

    @Test
    public void addInlineButtonInRowTextTest() {
        InlineKeyboardConstructor inlineKeyboardConstructor = new InlineKeyboardConstructor();
        inlineKeyboardConstructor.addInlineButtonInRow(TEST_STRING, TEST_STRING);
        inlineKeyboardConstructor.addNewInlineRow();
        assertEquals(inlineKeyboardConstructor.getInlineKeyboard().getKeyboard().get(0).get(0).getText(), TEST_STRING);
        assertEquals(inlineKeyboardConstructor.getInlineKeyboard().getKeyboard().get(0).get(0).getCallbackData(), TEST_STRING);
    }

    @Test
    public void addInlineButtonInRowNullKeyboardTest() {
        InlineKeyboardConstructor inlineKeyboardConstructor = new InlineKeyboardConstructor();
        inlineKeyboardConstructor.addNewInlineRow();
        inlineKeyboardConstructor.addNewInlineRow();
        inlineKeyboardConstructor.addNewInlineRow();
        assertNull(inlineKeyboardConstructor.getInlineKeyboard().getKeyboard());
    }

    @Test
    public void createInlineKeyboardNewsCategorySettingTest() {
        assertThrowsExactly(NullPointerException.class, () -> {SettingKeyboard.createInlineKeyboardNewsCategorySetting(null);});
    }
}
