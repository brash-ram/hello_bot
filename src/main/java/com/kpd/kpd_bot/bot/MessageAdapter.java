package com.kpd.kpd_bot.bot;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;
@Service
public class MessageAdapter {

	private final SendMessage sendMessage = new SendMessage();

	public MessageAdapter setChatId(long chatId) {
		sendMessage.setChatId(chatId);
		return this;
	}

	public MessageAdapter setChatId(String chatId) {
		sendMessage.setChatId(chatId);
		return this;
	}

	public MessageAdapter setText(String text) {
		sendMessage.setText(text);
		return this;
	}

	public MessageAdapter addReplyButtons(String... buttons) {
		ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
		sendMessage.setReplyMarkup(replyKeyboardMarkup);
		replyKeyboardMarkup.setSelective(true);
		replyKeyboardMarkup.setResizeKeyboard(true);
		replyKeyboardMarkup.setOneTimeKeyboard(false);
		List<KeyboardRow> keyboard = new ArrayList<>();

		for (String button: buttons) {
			KeyboardRow row = new KeyboardRow();
			row.add(button);
			keyboard.add(row);
		}
		replyKeyboardMarkup.setKeyboard(keyboard);
		return this;
	}

	public MessageAdapter addDeleteKeyboard() {
		ReplyKeyboardRemove keyboardRemove = new ReplyKeyboardRemove();
		sendMessage.setReplyMarkup(keyboardRemove);
		return this;
	}

	public SendMessage getSendMessage() {
		return sendMessage;
	}


}
