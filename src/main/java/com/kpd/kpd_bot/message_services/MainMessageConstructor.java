package com.kpd.kpd_bot.message_services;

import com.kpd.kpd_bot.statics.StringConst;

import java.io.IOException;

public class MainMessageConstructor {
	private static Adapter weatherAdapter = new WeatherAdapter();

	public static String getMessage() throws IOException {
		return StringConst.helloMessage;
	}
}
