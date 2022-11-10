package com.kpd.kpd_bot.entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Accessors(chain = true)
@Table(name = "settings")
public class UserSetting {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "time_send", nullable = false)
	private String timeSend;

	@Column(nullable = false)
	private String city;

	@Column(nullable = false)
	private String newsCategory;

	@Column(nullable = false)
	private Boolean sendMainMessage;
}
