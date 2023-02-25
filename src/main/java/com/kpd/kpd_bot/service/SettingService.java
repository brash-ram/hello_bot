package com.kpd.kpd_bot.service;

import com.kpd.kpd_bot.entity.UserSetting;
import com.kpd.kpd_bot.jpa.SettingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SettingService {

	@Value("${user.default.timeSend}")
	private String timeSendDefault;
	@Value("${user.default.city}")
	private String cityDefault;

	@Value("${user.default.newsCategory}")
	private String newsCategoryDefault;

	private final SettingRepository settingRepository;

	public UserSetting saveNewSetting() {
		return settingRepository.save(new UserSetting(null, timeSendDefault, cityDefault, newsCategoryDefault, true));
	}

	public UserSetting saveSetting(UserSetting userSetting) {
		return settingRepository.save(userSetting);
	}
}
