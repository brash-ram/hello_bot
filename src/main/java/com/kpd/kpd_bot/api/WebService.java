package com.kpd.kpd_bot.api;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
public class WebService {
    public Object[] makeRequestToObjects(String url, String uri, Object requestDto) {

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

    public Object makeRequestToObject(String url, String uri, Object requestDto) {

        WebClient client = WebClient
                .builder()
                .baseUrl(url)
                .build();

        return requestDto != null ? client.post()
                .uri(uri)
                .accept(APPLICATION_JSON)
                .body(BodyInserters.fromValue(requestDto))
                .retrieve()
                .bodyToMono(Object.class)
                .block() :
                client.post()
                        .uri(uri)
                        .accept(APPLICATION_JSON)
                        .retrieve()
                        .bodyToMono(Object.class)
                        .block();
    }

}
