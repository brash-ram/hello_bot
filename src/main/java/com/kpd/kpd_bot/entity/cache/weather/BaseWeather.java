package com.kpd.kpd_bot.entity.cache.weather;

import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Accessors(chain = true)
@Table(name = "base_weather")
public class BaseWeather {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @ElementCollection
    @Fetch(FetchMode.JOIN)
    private List<Weather> weather;
    @OneToOne
    @JoinColumn(name = "main_id")
    private WeatherMain main;
    @OneToOne
    @JoinColumn(name = "wind_id")
    private Wind wind;
    private String name;
    private Timestamp timeUpdate;
}