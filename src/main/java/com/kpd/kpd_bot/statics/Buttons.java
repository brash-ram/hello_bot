package com.kpd.kpd_bot.statics;

import com.kpd.kpd_bot.utils.InlineKeyboardConstructor;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Buttons {
	public static final String[] startButtons = {"Получить новости этого дня прямо сейчас", "Настройки", "Справка"};

	public static InlineKeyboardMarkup getSetTimeKeyboard() {
		return new InlineKeyboardConstructor()
				.addInlineButtonInRow("Назад", "backSetting")
				.addNewInlineRow()
				.addInlineButtonInRow("-3", "-3hour")
				.addInlineButtonInRow("-1", "-hour")
				.addInlineButtonInRow("Часы", "no")
				.addInlineButtonInRow("+1", "+hour")
				.addInlineButtonInRow("+3", "+3hour")
				.addNewInlineRow()
				.addInlineButtonInRow("-10", "-10min")
				.addInlineButtonInRow("-1", "-min")
				.addInlineButtonInRow("Минуты", "no")
				.addInlineButtonInRow("+1", "+min")
				.addInlineButtonInRow("+10", "+10min")
				.addNewInlineRow()
				.addInlineButtonInRow("Подтвердить", "setTimeSend")
				.getInlineKeyboard();
	}
}
