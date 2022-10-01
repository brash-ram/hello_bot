package com.kpd.kpd_bot.bot;

import com.kpd.kpd_bot.util.TimeSendInlineKeyboardHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
@RequiredArgsConstructor
public class InlineKeyboardHandler {
	private final TimeSendInlineKeyboardHandler timeSendInlineKeyboardHandler;

	public void handleMessage(Update update, Bot bot) throws TelegramApiException {
		String callData = update.getCallbackQuery().getData();
		int messageId = update.getCallbackQuery().getMessage().getMessageId();
		long chatId = update.getCallbackQuery().getMessage().getChatId();
		String messageText = update.getCallbackQuery().getMessage().getText();
		MessageAdapter newMessage = null;
		EditMessageText editMessage = null;

		switch (callData) {
			case "<<":
				editMessage = this.editMessage(chatId, messageId,
						timeSendInlineKeyboardHandler.subHour(messageText), update.getCallbackQuery().getMessage().getReplyMarkup());
				break;
			case ">>":
				editMessage = this.editMessage(chatId, messageId,
						timeSendInlineKeyboardHandler.addHour(messageText), update.getCallbackQuery().getMessage().getReplyMarkup());
				break;
		}

		if (newMessage != null) {
			bot.execute(newMessage.getSendMessage());
		}
		if (editMessage != null) {
			bot.execute(editMessage);
		}
	}

	private EditMessageText editMessage(long chatId, int messageId, String text, InlineKeyboardMarkup keyboardMarkup) {
		EditMessageText editMessage = new EditMessageText();
		editMessage.setChatId(chatId);
		editMessage.setMessageId(messageId);
		editMessage.setText(text);
		editMessage.setReplyMarkup(keyboardMarkup);
		return editMessage;
	}
}
