package com.kpd.kpd_bot.api.news.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class News {
    private String description;
    private String link;
    private List<String> category;
}
