package com.kpd.kpd_bot.entity;

import lombok.*;
import lombok.experimental.Accessors;
import org.glassfish.grizzly.http.util.TimeStamp;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Accessors(chain = true)
@Table(name = "settings")
public class Setting {
	@Id
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "time_send", nullable = false)
	private TimeStamp timeSend;

	@Column(nullable = false)
	private String city;

	@Column(nullable = false)
	private String currency;
}
