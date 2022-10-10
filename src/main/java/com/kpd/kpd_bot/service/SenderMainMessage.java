package com.kpd.kpd_bot.service;

import com.kpd.kpd_bot.bot.Bot;
import com.kpd.kpd_bot.bot.MessageAdapter;
import com.kpd.kpd_bot.entity.UserInfo;
import com.kpd.kpd_bot.util.DateGetter;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.UnsupportedEncodingException;

@Service
@RequiredArgsConstructor
public class SenderMainMessage {
	private final UserService userService;
	private final Bot bot;
	private final MainMessageConstructor mainMessageConstructor;

	@Scheduled(initialDelay = 5000, fixedRate = 600000)
	@Async
	public void sendMessage() throws TelegramApiException {
		String currentHour = DateGetter.getCurrentHourIfNewHour();
		if (!currentHour.equals(DateGetter.DEFAULT_HOUR)) {
			userService.findByHour(currentHour).forEach(
					userInfo -> {
						MessageAdapter newMessage = new MessageAdapter();
						try {
							newMessage.setText(mainMessageConstructor.getMessage(userInfo.getId())).setChatId(userInfo.getId());
						} catch (UnsupportedEncodingException e) {
							throw new RuntimeException(e);
						}

						try {
							bot.execute(newMessage.getSendMessage());
						} catch (TelegramApiException e) {
							throw new RuntimeException(e);
						}
					}
			);
		}
	}
}
