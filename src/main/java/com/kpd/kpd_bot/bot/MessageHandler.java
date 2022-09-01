package com.kpd.kpd_bot.bot;

import org.telegram.telegrambots.meta.api.objects.Update;

public class MessageHandler {

	public static void handleMessage(Update update){
		String message = update.getMessage().getText();

//		switch (message) {
//			default ->
//		}
	}
}
