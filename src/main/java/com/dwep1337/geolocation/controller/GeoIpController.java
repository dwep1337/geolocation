package com.dwep1337.geolocation.controller;

import com.dwep1337.geolocation.dto.GeoIPResponse;
import com.dwep1337.geolocation.service.GeoIpService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api")
public class GeoIpController {

    private final GeoIpService geoIpService;

    @GetMapping()
    public GeoIPResponse getLocation(@RequestParam String ip) {
        return geoIpService.getLocation(ip);
    }

}
