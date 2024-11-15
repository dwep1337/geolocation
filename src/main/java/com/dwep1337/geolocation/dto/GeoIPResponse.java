package com.dwep1337.geolocation.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GeoIPResponse {
    private boolean success;
    private String message;
    private String ipAddress;
    private String country;
    private String countryIsoCode;
    private String city;
    private String subdivision;
    private Double latitude;
    private Double longitude;
    private String postalCode;
    private Integer postalConfidence;
    private Boolean isVpn;
    private Boolean isProxy;
    private String timezone;
    private String continent;
    private Integer metroCode;
}