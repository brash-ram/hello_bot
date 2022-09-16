package com.kpd.kpd_bot.message_services;

import com.kpd.kpd_bot.statics.StringConst;

public class MainMessageConstructor {
	private static Adapter weatherAdapter = new WeatherAdapter();

	public static String getMessage() {
		return StringConst.helloMessage + "\n" + weatherAdapter.getTextFromMessageService();
	}
}
