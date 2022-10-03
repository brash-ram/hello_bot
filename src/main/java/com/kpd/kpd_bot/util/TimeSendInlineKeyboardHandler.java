package com.kpd.kpd_bot.util;

import org.springframework.stereotype.Service;

@Service
public class TimeSendInlineKeyboardHandler {
	public String addHour(String text) {
		return this.changeHour(text, 1);
 	}

	public String subHour(String text) {
		return this.changeHour(text, -1);
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
