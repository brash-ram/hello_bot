package com.kpd.kpd_bot.entity.cache;

import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Accessors(chain = true)
@Table(name = "news")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private Date dateUpdate;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String link;

//    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinColumn(name = "new_categories", nullable = false)
    @ElementCollection
    @Fetch(FetchMode.JOIN)
    private Set<String> categories = new HashSet<>();
//    private String categories;
}
