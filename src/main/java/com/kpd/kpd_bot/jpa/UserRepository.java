package com.kpd.kpd_bot.jpa;

import com.kpd.kpd_bot.entity.UserInfo;
import com.kpd.kpd_bot.entity.UserSetting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserInfo, Long> {
	List<UserInfo> findByUserSettingIn(List<UserSetting> userSettings);
}