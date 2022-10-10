package com.kpd.kpd_bot.api;

import java.io.UnsupportedEncodingException;

public interface Adapter {

	public abstract String getTextFromMessageService(String... args) throws UnsupportedEncodingException;
}
