package com.kpd.kpd_bot.jpa;

import com.kpd.kpd_bot.entity.UserSetting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SettingRepository extends JpaRepository<UserSetting, Long> {
}