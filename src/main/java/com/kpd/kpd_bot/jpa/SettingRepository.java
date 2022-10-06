package com.kpd.kpd_bot.jpa;

import com.kpd.kpd_bot.entity.UserSetting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SettingRepository extends JpaRepository<UserSetting, Long> {
	List<UserSetting> findByTimeSend(String timeSend);
}