package com.kpd.kpd_bot.util;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TimeSendInlineKeyboardHandler {
	public static final List<String> listTimeButtons = new ArrayList<String>(Arrays.asList("-3hour", "-hour", "+hour", "+3hour", "-10min", "-min", "+min", "+10min"));
	public String getNewTime(String oldTime, String param) {
		String newTime = oldTime;
		switch (param) {
			case "-3hour" -> newTime = changeHour(oldTime, -3);
			case "-hour" -> newTime = changeHour(oldTime, -1);
			case "+hour" -> newTime = changeHour(oldTime, 1);
			case "+3hour" -> newTime = changeHour(oldTime, 3);

			case "-10min" -> newTime = changeMinutes(oldTime, -10);
			case "-min" -> newTime = changeMinutes(oldTime, -1);
			case "+min" -> newTime = changeMinutes(oldTime, 1);
			case "+10min" -> newTime = changeMinutes(oldTime, 10);
		}
		return newTime;
	}

	private String changeHour(String oldTime, int changeTime) {
		String[] bigTime = oldTime.split(":");
		int time = Integer.parseInt(bigTime[0]);
		int newTime = (time + changeTime) % 24;
		if (newTime < 0) {
			newTime = 24 + newTime;
		}
		return newTime +":"+bigTime[1];
	}

	private String changeMinutes(String oldTime, int changeTime) {
		String[] bigTime = oldTime.split(":");
		int time = Integer.parseInt(bigTime[1]);
		int newTime = (time + changeTime) % 60;
		String result = oldTime;
		if (newTime < 0) {
			newTime = 60 + newTime;
			result = changeHour(createNewTimeForMinutes(bigTime[0], newTime), -1);
		} else if (time+changeTime >= 60) {
			result = changeHour(createNewTimeForMinutes(bigTime[0], newTime), 1);
		} else {
			result = createNewTimeForMinutes(bigTime[0], newTime);
		}
		return result;
	}

	private String createNewTimeForMinutes(String hour, int minutes) {
		String result = "";
		if (0 <= minutes && minutes <= 9) {
			result = hour+":"+"0"+minutes;
		} else {
			result = hour+":"+minutes;
		}
		return result;
	}
}
