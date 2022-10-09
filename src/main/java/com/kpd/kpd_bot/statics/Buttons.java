package com.kpd.kpd_bot.statics;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Buttons {
	public static final String[] startButtons = {"Получить новости этого дня прямо сейчас", "Настройки", "Справка"};
	public static final String[] settingsButtons = {"Настроить время отправки сообщения",
													"Настроить форму обращения",
													"Настроить информационные параметры сообщения"};

	public static final String[] infoSettingsButtons = {""};

}
