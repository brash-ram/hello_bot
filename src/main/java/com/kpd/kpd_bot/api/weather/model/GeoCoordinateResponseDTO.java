package com.kpd.kpd_bot.api.weather.model;

import lombok.Data;

import java.util.List;

@Data
public class GeoCoordinateResponseDTO {
    private List<GeoCoordinate> coordinateList;
}
