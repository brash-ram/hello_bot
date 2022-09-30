package com.kpd.kpd_bot.jpa;

import com.kpd.kpd_bot.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserInfo, Long> {
}