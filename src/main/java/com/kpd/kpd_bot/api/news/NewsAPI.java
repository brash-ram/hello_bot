package com.kpd.kpd_bot.api.news;

import com.kpd.kpd_bot.api.news.model.BaseNewsResponseDTO;
import com.kpd.kpd_bot.entity.cache.News;
import com.kpd.kpd_bot.config.NewsConfig;
import com.kpd.kpd_bot.service.WebService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class NewsAPI {

    private final WebService webService;
    private final NewsConfig newsConfig;

    private String getUrl() {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme("https").host(newsConfig.getUrl()).path("news?apikey={API_KEY}&country={country}&" +
                        "language={language}&category={category}")
                .buildAndExpand(newsConfig.getToken(), "ru", "ru", "technology");
        return uriComponents.toUriString();
    }

    public News getNews() {
        BaseNewsResponseDTO responseDTO = webService.<BaseNewsResponseDTO>makeGetRequest(this.getUrl(), BaseNewsResponseDTO.class);
        return getLatestTechnologyNews(responseDTO);
    }

    private News getLatestTechnologyNews(BaseNewsResponseDTO dto) {
        return dto.getResults()
                .stream()
                .findFirst()
                .get();
    }
}
