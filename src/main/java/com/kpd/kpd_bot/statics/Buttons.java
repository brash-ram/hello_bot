package com.kpd.kpd_bot.statics;

import com.kpd.kpd_bot.util.InlineKeyboardConstructor;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Buttons {
	public static final String[] startButtons = {"Получить новости этого дня прямо сейчас", "Настройки", "Справка"};

	public static InlineKeyboardMarkup getSettingButtons() {
		return new InlineKeyboardConstructor()
				.addInlineButtonInRow("Настроить время отправки сообщения", "setSendingMessageTime")
				.addNewInlineRow()
				.addInlineButtonInRow("Настроить параметры сообщения", "setMessageInfoParameters")
				.addNewInlineRow()
				.addInlineButtonInRow("Настроить форму обращения в приветствии", "setUserForm")
				.getInlineKeyboard();
	}
}
