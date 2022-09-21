package com.kpd.kpd_bot.api;

import com.kpd.kpd_bot.config.WeatherConfig;
import com.kpd.kpd_bot.dto.request.WeatherApiRequestDTO;
import lombok.RequiredArgsConstructor;
import org.jvnet.hk2.annotations.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
@RequiredArgsConstructor
public class WebService {
    public String makeRequest(String url, Object dto) {
        WeatherApiRequestDTO requestDTO = new WeatherApiRequestDTO();

        WebClient client = WebClient.create(url);
        YandexIAMTokenResponseDTO responseDTO = client.post()
                .accept(APPLICATION_JSON)
                .body(BodyInserters.fromValue(dto))
                .retrieve()
                .bodyToMono(YandexIAMTokenResponseDTO.class)
                .block();

        return null;
    }
}
