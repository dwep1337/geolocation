package com.dwep1337.geolocation.dto;

import lombok.Builder;

@Builder
public record GeoIPResponse(
        Boolean success,
        String ipAddress,
        String country,
        String countryIsoCode,
        String city,
        String subdivision,
        Double latitude,
        Double longitude,
        String postalCode,
        Integer postalConfidence,
        Boolean isVpn,
        Boolean isProxy,
        String timezone,
        String continent,
        Integer metroCode
) {
}
