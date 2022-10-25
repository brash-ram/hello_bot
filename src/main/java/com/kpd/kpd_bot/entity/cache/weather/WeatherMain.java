package com.kpd.kpd_bot.entity.cache.weather;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Accessors(chain = true)
@Table(name = "WeatherMain")
public class WeatherMain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private double temp;
    private double feels_like;
    private int pressure;
    private int humidity;
}
