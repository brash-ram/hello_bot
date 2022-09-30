package com.kpd.kpd_bot.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateGetter {
	public static Date getSqlDate() {
		return new Date(System.currentTimeMillis());
	}
}
