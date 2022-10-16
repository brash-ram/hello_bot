package com.kpd.kpd_bot.api;

import org.springframework.scheduling.annotation.Async;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.Future;

public interface Adapter {
	@Async
	public Future<String> getTextFromMessageService(String... args) throws UnsupportedEncodingException;
}
