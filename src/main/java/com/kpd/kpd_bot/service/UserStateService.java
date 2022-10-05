package com.kpd.kpd_bot.service;

import com.kpd.kpd_bot.entity.UserState;
import com.kpd.kpd_bot.jpa.UserStateRepository;
import com.kpd.kpd_bot.myenum.UserStateEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserStateService {
	private final UserStateRepository userStateRepository;

	public UserState saveUserState(Long userId, UserStateEnum userState) {
		return userStateRepository.save(new UserState(null, userId, userState));
	}

	public UserState findUserState(Long userId) {
		return userStateRepository.findByUserId(userId);
	}

	public void deleteUserState(Long userId) {
		userStateRepository.deleteByUserId(userId);
	}
}
