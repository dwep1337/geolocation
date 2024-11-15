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
        // Validate the provided IP address
        if (!validIP.isValidIp(ip)) {
            log.warn("Invalid IP: {}", ip);
            return GeoIPResponse.builder()
                    .success(false)
                    .message("Invalid IP address")
                    .build();
        }

        try {
            // Load the GeoLite2 database
            File database = new File("src/main/resources/GeoLite2-City.mmdb");
            if (!database.exists()) {
                log.error("GeoLite2 database file not found");
                return GeoIPResponse.builder()
                        .success(false)
                        .message("GeoLite2 database file not found")
                        .build();
            }

            DatabaseReader dbReader = new DatabaseReader.Builder(database).build();
            InetAddress ipAddress = InetAddress.getByName(ip);

            // Query geolocation information
            CityResponse response = dbReader.city(ipAddress);

            return GeoIPResponse.builder()
                    .success(true)
                    .message("Success")
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

        } catch (IOException e) {
            log.error("Error accessing the GeoLite2 database or reading the file", e);
            return GeoIPResponse.builder()
                    .success(false)
                    .message("Error accessing the database")
                    .build();
        } catch (GeoIp2Exception e) {
            log.error("Error processing the IP query for: {}", ip, e);
            return GeoIPResponse.builder()
                    .success(false)
                    .message("Error processing the IP query")
                    .build();
        } catch (Exception e) {
            log.error("Unexpected error processing the IP: {}", ip, e);
            return GeoIPResponse.builder()
                    .success(false)
                    .message("Unexpected error occurred")
                    .build();
        }
    }
}
