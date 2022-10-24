package com.kpd.kpd_bot.entity.cache;

import com.kpd.kpd_bot.api.film.model.Country;
import com.kpd.kpd_bot.api.film.model.Genres;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Accessors(chain = true)
@Table(name = "films")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private Date dateUpdate;

    private String nameRu;
    private int year;
    private int duration;
    private String premiereRu;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "film_country", nullable = false)
    private Set<Country> countries;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "film_genre", nullable = false)
    private Set<Genres> genres;

}
