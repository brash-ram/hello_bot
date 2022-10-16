package com.kpd.kpd_bot.api.exchangerate;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseExchangeRateResponseDTO {
    private String base;
    private Map<String, Double> rates;
}
