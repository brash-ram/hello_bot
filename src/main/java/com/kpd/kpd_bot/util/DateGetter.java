package com.kpd.kpd_bot.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateGetter {
	public static final String DEFAULT_HOUR = "no date";
	public static Date getSqlDate() {
		return new Date(System.currentTimeMillis());
	}

	public static String getCurrentHourIfNewHour() {
		LocalTime now = LocalTime.now();
		String result = DateGetter.DEFAULT_HOUR;
		int nowMinute = now.getMinute();
		if (nowMinute >= 55 || nowMinute <= 5) {
			result = String.valueOf(now.getHour());
		}
		return result;
	}

	public static String getMessageWithTime() {
		LocalTime now = LocalTime.now();
		String result = "";
		if ((now.getHour() >= 5) && (now.getHour() < 12)) {
			result = "Доброе утро, ";
		} else if ((now.getHour() >= 12) && (now.getHour() < 17)) {
			result = "Добрый день, ";
		} else if ((now.getHour() >= 17) && (now.getHour() < 22)) {
			result = "Добрый вечер, ";
		} else if (((now.getHour() >= 22) && (now.getHour() < 23)) || (now.getHour() >= 0) && (now.getHour() < 5)) {
			result = "Доброй ночи, ";
		}
		return result;
	}
}
