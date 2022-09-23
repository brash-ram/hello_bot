package com.kpd.kpd_bot.api;

import com.kpd.kpd_bot.config.WeatherConfig;
import com.kpd.kpd_bot.dto.request.WeatherApiRequestDTO;
import lombok.RequiredArgsConstructor;
import org.jvnet.hk2.annotations.Service;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
public class WebService {
    public Object[] makeRequest(String url, String uri, Object requestDto) {

        WebClient client = WebClient
                .builder()
                .baseUrl(url)
                .build();

        return requestDto != null ? client.post()
                .uri(uri)
                .accept(APPLICATION_JSON)
                .body(BodyInserters.fromValue(requestDto))
                .retrieve()
                .bodyToMono(Object[].class)
                .block() :
                client.post()
                        .uri(uri)
                        .accept(APPLICATION_JSON)
                        .retrieve()
                        .bodyToMono(Object[].class)
                        .block();
    }

}
