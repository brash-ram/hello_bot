package com.kpd.kpd_bot.bot;

import com.kpd.kpd_bot.config.BotConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.UnsupportedEncodingException;


@Component
@RequiredArgsConstructor
public class Bot extends TelegramLongPollingBot {

	private final BotConfig botConfig;
	private final MessageHandler messageHandler;
	private final InlineKeyboardHandler inlineKeyboardHandler;

	@Override
	public String getBotToken() {
		return botConfig.getToken();
	}

	@Override
	public void onUpdateReceived(Update update) {
		if (update.hasMessage() && update.getMessage().hasText()) {

			try {
				messageHandler.handleMessage(update, this);
			} catch (RuntimeException | TelegramApiException | UnsupportedEncodingException e) {
				e.printStackTrace();
			}

		}
		else if (update.hasCallbackQuery()) {

			try {
				inlineKeyboardHandler.handleMessage(update, this);
			} catch (RuntimeException | TelegramApiException e) {
				e.printStackTrace();
			}

		}
	}

	@Override
	public String getBotUsername() {
		return botConfig.getUsernameBot();
	}

}
