package com.kpd.kpd_bot.service;

import com.kpd.kpd_bot.entity.UserSetting;
import com.kpd.kpd_bot.jpa.SettingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SettingService {

	@Value("${user.time.send.default}")
	private String timeSendDefault;
	@Value("${user.city.default}")
	private String cityDefault;

	private final SettingRepository settingRepository;

	public UserSetting saveNewSetting() {
		return settingRepository.save(new UserSetting(null, timeSendDefault, cityDefault));
	}

	public UserSetting saveSetting(UserSetting userSetting) {
		return settingRepository.save(userSetting);
	}
}
