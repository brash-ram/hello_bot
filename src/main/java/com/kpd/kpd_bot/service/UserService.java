package com.kpd.kpd_bot.service;

import com.kpd.kpd_bot.entity.ExchangeRatesSetting;
import com.kpd.kpd_bot.entity.Subscription;
import com.kpd.kpd_bot.entity.UserSetting;
import com.kpd.kpd_bot.entity.UserInfo;
import com.kpd.kpd_bot.jpa.SettingRepository;
import com.kpd.kpd_bot.jpa.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;

	private final SettingService settingService;
	private final SubscriptionService subscriptionService;
	private final ExchangeRatesSettingService exchangeRatesSettingService;
	private final SettingRepository settingRepository;

	public UserInfo saveNewUser(User user) {
		UserSetting setting = settingService.saveNewSetting();
		Subscription subscription = subscriptionService.saveNewSubscription();
		ExchangeRatesSetting exchangeRatesSetting = exchangeRatesSettingService.saveNewExchangeRatesSetting();
		UserInfo newUser = new UserInfo(user.getId(), user.getFirstName(), setting, subscription, exchangeRatesSetting);
		return userRepository.save(newUser);
	}

	public UserInfo saveUser(UserInfo userInfo) {
		return userRepository.save(userInfo);
	}
	public boolean existUser(Long userId) {
		return userRepository.existsById(userId);
	}

	public UserInfo findById(Long userId) {return userRepository.findById(userId).get();}
	public List<UserInfo> findByHour(String hour) {
		return userRepository.findByUserSettingIn(settingRepository.findByTimeSend(hour));
	}


}
