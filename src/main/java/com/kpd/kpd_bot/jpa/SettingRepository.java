package com.kpd.kpd_bot.jpa;

import com.kpd.kpd_bot.entity.Setting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SettingRepository extends JpaRepository<Setting, Long> {
}