package com.kpd.kpd_bot.util;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateGetter {

	public static final String DATE_PATTERN = "dd MMMM yyyy";
	public static final String DEFAULT_HOUR = "no date";
	public static Date getSqlDate() {
		return new Date(System.currentTimeMillis());
	}
	public static Timestamp getTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}

	public static String getCurrentTimeInNeededFormatted() {
		LocalTime now = LocalTime.now();
		return (now.getHour()+3) + ":" + now.getMinute();
	}

	public static String getMessageWithTime() {
		LocalTime now = LocalTime.now();
		String result;
		if ((now.getHour() >= 5) && (now.getHour() < 12)) {
			result = "Доброе утро, ";
		} else if ((now.getHour() >= 12) && (now.getHour() < 17)) {
			result = "Добрый день, ";
		} else if ((now.getHour() >= 17) && (now.getHour() < 22)) {
			result = "Добрый вечер, ";
		} else {
			result = "Доброй ночи, ";
		}
		return result;
	}

	public static String getFormattedDate(String date, String inputFormat) {
		LocalDate inputDate = LocalDate.parse(date, DateTimeFormatter.ofPattern(inputFormat));
		return inputDate.format(DateTimeFormatter.ofPattern(DATE_PATTERN, new Locale("ru"))).toString();
	}

	public static int getDifferanceDay(Date date) {
		return Period.between(LocalDate.parse(date.toString()), LocalDate.now()).getDays();
	}

	public static int getDifferanceHour(Timestamp time) {
		LocalDateTime now = LocalDateTime.now();
		return now.getDayOfMonth() - time.toLocalDateTime().getDayOfMonth() == 0 ?
		now.getHour() - time.toLocalDateTime().getHour() : 1;
	}
}
