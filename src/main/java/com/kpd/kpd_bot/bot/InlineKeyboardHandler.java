package com.kpd.kpd_bot.bot;

import com.kpd.kpd_bot.util.TimeSendInlineKeyboardHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
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
		MessageAdapter newMessage = new MessageAdapter().setChatId(chatId);
		EditMessageText editMessage = new EditMessageText();

		switch (callData) {
			case "<<":
				editMessage.setChatId(chatId);
				editMessage.setMessageId(messageId);
				editMessage.setText(timeSendInlineKeyboardHandler.subHour(messageText));
				editMessage.setReplyMarkup(timeSendInlineKeyboardHandler.getKeyboard());
				newMessage = null;
				break;
			case ">>":
				editMessage.setChatId(chatId);
				editMessage.setMessageId(messageId);
				editMessage.setText(timeSendInlineKeyboardHandler.addHour(messageText));
				editMessage.setReplyMarkup(timeSendInlineKeyboardHandler.getKeyboard());
				newMessage = null;
				break;
		}

		if (newMessage != null) {
			bot.execute(newMessage.getSendMessage());
		}
		if (editMessage != null) {
			bot.execute(editMessage);
		}
	}

//	private EditMessageText editMessage(long chatId, int messageId, String text) {
//
//	}
}
