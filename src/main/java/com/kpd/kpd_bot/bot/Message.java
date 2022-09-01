package com.kpd.kpd_bot.bot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class Message {

	private final SendMessage sendMessage = new SendMessage();

	public Message() {}

	public Message setChatId(String chatId) {
		sendMessage.setChatId(chatId);
		return this;
	}

	public Message setText(String text) {
		sendMessage.setText(text);
		return this;
	}




}
