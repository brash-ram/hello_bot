package com.kpd.kpd_bot.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
public class WebService {

    public <T> T makeRequest(String url, String uri, Class<T> typeResponse) {

        WebClient client = WebClient.builder()
                .baseUrl(url)
                .build();

        return client.post()
                .uri(uri)
                .accept(APPLICATION_JSON)
                .retrieve()
                .bodyToMono(typeResponse)
                .block();
    }

    public <T> T makeRequest(String url, String uri, Object requestDto, Class<T> typeResponse) {

        WebClient client = WebClient.builder()
                .baseUrl(url)
                .build();

        return client.post()
                .uri(uri)
                .accept(APPLICATION_JSON)
                .body(BodyInserters.fromValue(requestDto))
                .retrieve()
                .bodyToMono(typeResponse)
                .block();
    }

}
