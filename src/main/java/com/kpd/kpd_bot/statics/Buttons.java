package com.kpd.kpd_bot.statics;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Buttons {
	public static final String[] startButtons = {"Настройки", "Новости этого дня"};
	public static final String[] settingsButtons = {"Настроить время отправки сообщения",
													"Настроить форму обращения к пользователю",
													"Настройка информационных параметров сообщения"};

	public static final String[] infoSettingsButtons = {""};

}
