package com.dwep1337.geolocation.service;

import com.dwep1337.geolocation.dto.GeoIPResponse;
import com.dwep1337.geolocation.util.ValidIP;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

@Service
@Log4j2
@RequiredArgsConstructor
public class GeoIpService {

    private final ValidIP validIP;

    public GeoIPResponse getLocation(String ip) {
        try {

            if (!validIP.isValidIp(ip)) {
                return GeoIPResponse.builder().success(false).build();
            }

            //read database
            File database = new File("src/main/resources/GeoLite2-City.mmdb");
            DatabaseReader dbReader = new DatabaseReader.Builder(database).build();
            InetAddress ipAddress = InetAddress.getByName(ip);
            CityResponse response = dbReader.city(ipAddress);

            return GeoIPResponse.builder()
                    .success(true)
                    .ipAddress(ip)
                    .country(response.getCountry().getName())
                    .countryIsoCode(response.getCountry().getIsoCode())
                    .city(response.getCity().getName())
                    .subdivision(response.getMostSpecificSubdivision().getName())
                    .latitude(response.getLocation().getLatitude())
                    .longitude(response.getLocation().getLongitude())
                    .postalCode(response.getPostal().getCode())
                    .postalConfidence(response.getPostal().getConfidence())
                    .metroCode(response.getLocation().getMetroCode())
                    .isProxy(response.getTraits().isPublicProxy())
                    .isVpn(response.getTraits().isAnonymous())
                    .timezone(response.getLocation().getTimeZone())
                    .continent(response.getContinent().getName())
                    .build();

        } catch (IOException | GeoIp2Exception e) {
            log.error("Cannot get location for ip: {}", ip, e);
            return GeoIPResponse.builder().success(false).build();
        }
    }
}
