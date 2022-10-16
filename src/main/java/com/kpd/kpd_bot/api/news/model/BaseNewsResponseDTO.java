package com.kpd.kpd_bot.api.news.model;

import com.kpd.kpd_bot.entity.cache.News;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseNewsResponseDTO {
    private List<News> results;
}
