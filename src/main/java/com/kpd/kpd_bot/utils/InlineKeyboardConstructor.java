package com.kpd.kpd_bot.utils;


import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class InlineKeyboardConstructor {
	private List<List<InlineKeyboardButton>> inlineKeyboard = new ArrayList<>();
	private List<InlineKeyboardButton> currentRowInlineButtons = new ArrayList<>();

	public List<InlineKeyboardButton> getInlineKeyboardRow(int index) {
		return inlineKeyboard.get(index);
	}

	public int size() {
		return inlineKeyboard.size();
	}

	public InlineKeyboardConstructor addInlineButtonInRow(String buttonText, String callbackData) {
		InlineKeyboardButton button = new InlineKeyboardButton();
		button.setText(buttonText);
		button.setCallbackData(callbackData);
		currentRowInlineButtons.add(button);
		return this;
	}

	public InlineKeyboardConstructor addNewInlineRow() {
		if (currentRowInlineButtons.size() != 0) {
			inlineKeyboard.add(currentRowInlineButtons);
			currentRowInlineButtons = new ArrayList<>();
		}
		return this;
	}

	public InlineKeyboardMarkup getInlineKeyboard() {
		InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
		if (inlineKeyboard.size() != 0 || currentRowInlineButtons.size() != 0) {
			this.addNewInlineRow();
			keyboard.setKeyboard(inlineKeyboard);
		}
		return keyboard;
	}

	private InlineKeyboardConstructor setInlineKeyboard(InlineKeyboardMarkup keyboard) {
		inlineKeyboard = keyboard.getKeyboard();
		return this;
	}
}
