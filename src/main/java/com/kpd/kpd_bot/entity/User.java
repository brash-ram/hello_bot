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
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_setting", nullable = false)
	private Setting setting;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_subscription", nullable = false)
	private Subscription  subscription;

}
