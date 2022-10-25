package com.kpd.kpd_bot.api.exchangerate;

import com.kpd.kpd_bot.config.ExchangeRateConfig;
import com.kpd.kpd_bot.entity.cache.ExchangeRate;
import com.kpd.kpd_bot.service.WebService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class ExchangeRatesAPI {
    private final ExchangeRateConfig exchangeRateConfig;
    private final WebService webService;

    private String getUrl() {
        return UriComponentsBuilder.newInstance()
                .scheme("https").host(exchangeRateConfig.getUrl()).path("latest?base={base}&symbols={symbols}")
                .buildAndExpand( "RUB", "USD,EUR,GBP,CNY,CHF,JPY")
                .toUriString();
    }

    public ExchangeRate getExchangeRate() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("apikey", exchangeRateConfig.getToken());
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return webService.<ExchangeRate>makeGetRequest(this.getUrl(), ExchangeRate.class, headers);
    }
}