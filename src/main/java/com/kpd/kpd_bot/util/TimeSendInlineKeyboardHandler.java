package com.kpd.kpd_bot.util;

import com.kpd.kpd_bot.bot.MessageAdapter;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

@Service
public class TimeSendInlineKeyboardHandler {
	public String addHour(String text) {
		return this.changeHour(text, 1);
 	}

	public String subHour(String text) {
		return this.changeHour(text, -1);
	}

	public InlineKeyboardMarkup getKeyboard () {
		MessageAdapter message = new MessageAdapter();
		message.addInlineButtonInRow("<<", "<<").addInlineButtonInRow(">>", ">>")
					.addNewInlineRow().addInlineButtonInRow("Подтвердить", "setTimeSend");
		return message.getInlineKeyboard();
	}

	private String changeHour(String text, int changeTime) {
		int time = Integer.parseInt(text);
		time = (time + changeTime) % 24;
		if (time < 0) {
			time = 23;
		}
		return String.valueOf(time);
	}
}
