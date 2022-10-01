package com.kpd.kpd_bot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
public class WebService {

    private final WebClient client;

    public WebService() {
        this.client = WebClient.create();
    }

    public <T> T makeRequest(String url, Class<T> typeResponse) {
        return client.post()
                .uri(url)
                .accept(APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(typeResponse)
                .block();
    }

    public <T> T makeRequest(String url, Object requestDto, Class<T> typeResponse) {
        return client.post()
                .uri(url)
                .accept(APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(BodyInserters.fromValue(requestDto))
                .retrieve()
                .bodyToMono(typeResponse)
                .block();
    }

}
