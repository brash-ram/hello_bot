package com.kpd.kpd_bot.bot;

import com.kpd.kpd_bot.config.BotConfig;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


@Component
@AllArgsConstructor
public class Bot extends TelegramLongPollingBot {


	private BotConfig botConfig;

	@Override
	public String getBotToken() {
		return botConfig.getToken();
	}

	@Override
	public void onUpdateReceived(Update update) {
		if (update.hasMessage() && update.getMessage().hasText()) {
			MessageHandler.handleMessage(update);
		}
//		if (update.hasMessage() && update.getMessage().hasText()) {
//			SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
//			message.setChatId(update.getMessage().getChatId().toString());
//			message.setText(update.getMessage().getText());
//
//			try {
//				execute(message); // Call method to send the message
//			} catch (TelegramApiException e) {
//				e.printStackTrace();
//			}
//		}
	}

	@Override
	public String getBotUsername() {
		return botConfig.getUsernameBot();
	}

	public void sendMessage(SendMessage sendMessage) {
		try {
			execute(sendMessage);
		} catch (TelegramApiException e) {

		}
	}
}
