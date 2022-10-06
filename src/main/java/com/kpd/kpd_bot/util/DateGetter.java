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
}
