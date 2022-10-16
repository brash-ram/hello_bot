package com.kpd.kpd_bot.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
public class WebService {

    private final WebClient client;

    public WebService() {
        this.client = WebClient.create();
    }

    public <T> T makePostRequest(String url, Class<T> typeResponse) {
        return client.post()
                .uri(url)
                .accept(APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .onStatus(HttpStatus::isError, clientResponse -> Mono.error(RuntimeException::new))
                .bodyToMono(typeResponse)
                .onErrorResume(throwable -> null)
                .blockOptional()
                .orElseGet(() -> null);
    }

    public <T> T makeGetRequest(String url, Class<T> typeResponse) {
        return client.get()
                .uri(url)
                .accept(APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .onStatus(HttpStatus::isError, clientResponse -> Mono.error(RuntimeException::new))
                .bodyToMono(typeResponse)
                .blockOptional()
                .orElseGet(() -> null);
    }

    public <T> T makeGetRequest(String url, Class<T> typeResponse, HttpHeaders headers) {
        return client.get()
                .uri(url)
                .accept(APPLICATION_JSON)
                .headers(httpHeaders -> httpHeaders.addAll(headers))
                .retrieve()
                .onStatus(HttpStatus::isError, clientResponse -> Mono.error(RuntimeException::new))
                .bodyToMono(typeResponse)
                .blockOptional()
                .orElseThrow(RuntimeException::new);
    }

    public <T> T makePostRequest(String url, Object requestDto, Class<T> typeResponse) {
        return client.post()
                .uri(url)
                .accept(APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(BodyInserters.fromValue(requestDto))
                .retrieve()
                .onStatus(HttpStatus::isError, clientResponse -> Mono.error(RuntimeException::new))
                .bodyToMono(typeResponse)
                .blockOptional()
                .orElseGet(() -> null);
    }

}
