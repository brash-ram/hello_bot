package com.kpd.kpd_bot.entity.cache;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Accessors(chain = true)
@Table(name = "exchange_rates")
public class ExchangeRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private Date dateUpdate;
    private String base;

    @ElementCollection
    @Fetch(FetchMode.JOIN)
    @CollectionTable(name = "rates",
            joinColumns = {@JoinColumn(name = "rates_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "item_name")
    @Column(name = "rates_map")
    private Map<String, Double> rates = new HashMap<>();
}
