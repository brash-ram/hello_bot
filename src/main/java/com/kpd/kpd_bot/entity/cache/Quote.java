package com.kpd.kpd_bot.entity.cache;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Accessors(chain = true)
@Table(name = "quotes")
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private Date dateUpdate;
    private String quote;
    private String author;

}
