package com.kpd.kpd_bot.statics;


import java.util.Map;

import static java.util.Map.entry;

public class StringConst {
	public static final String START_MESSAGE = "Привет! Я бот, который будет служить вашим ежедневным помощником. " +
												"Для продолжения выберите один из пунктов меню";

	public static final String SETTINGS_MESSAGE = "Вы можете настроить время отправки и информационные параметры сообщения," +
												  " а также форму обращения к вам";

	public static final String HELLO_MESSAGE = "Привет, ";

	public static final String HELP_MESSAGE = "Данный телеграм-бот предназначен для " +
			"ежедневной отправки приветственного сообщения с основной информацией дня: курсы валют, погода " +
			"(по месту, которое указал пользователь), цитата дня, фильм дня, новости. " +
			"В пункте меню \"Настройки\" вы можете выбрать информацию, которую хотите видеть в сообщении, " +
			"форму обращения к вам и время отправки сообщения.";
	public static final String NEWS_PARAMETERS_MESSAGE = "Что вы хотите видеть в сообщении?";
	public static final String START_TIME_SEND = "10";

	public static final String DEFAULT_MESSAGE = "Выберите необходимый пункт меню для продолжения";

	public static final String WEATHER = "Погода";
	public static final String QUOTE = "Цитата дня";
	public static final String FILM = "Фильм дня ";
	public static final String EXCHANGE_RATES = "Курс валют";
	public static final String NEWS = "Новости ";
	public static final String BACK = "Назад";
	public static final String GOOD_INPUT_NAME_FOR_USER = "Вы успешно изменили обращение к себе!";
	public static final String INPUT_NAME_FOR_USER = "Напишите, как вы хотите, чтобы к вам обращались в сообщении.";
	public static final String INPUT_CITY_FOR_USER = "Укажите город, по которому вы хотели бы получить прогноз погоды.";
	public static final String GOOD_INPUT_CITY_FOR_USER = "Вы успешно изменили город!";
	public static final String CHF_RUB = "CHF/RUB";
	public static final String JPY_RUB = "JPY/RUB";
	public static final String EUR_RUB = "EUR/RUB";
	public static final String CNY_RUB = "CNY/RUB";
	public static final String GBP_RUB = "GBP/RUB";
	public static final String USD_RUB = "USD/RUB";
	public static final String SUCCESSFULLY_SET_TIME_SEND = "Вы успешно изменили время отправки сообщения";
	public static final String SET_CURRENCIES = "Выберите необходимые валюты";
	public static final String SET_NEWS_CATEGORY = "Выберите категорию новостей";
	public static final Map<String, String> NEWS_CATEGORIES = Map.ofEntries(
			entry("business", "Бизнес"),
			entry("politics", "Политика"),
			entry("science", "Наука"),
			entry("sports", "Спорт"),
			entry("technology", "Технологии"),
			entry("top", "Случайная новость")
	);
}
