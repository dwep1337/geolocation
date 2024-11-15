package com.dwep1337.geolocation.util;

import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ValidIP {

    // Regex for IPv4
    private static final String IPV4_PATTERN =
            "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";

    // Regex for IPv6 (updated to handle '::' and other variations)
    private static final String IPV6_PATTERN =
            "([0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,7}:|::([0-9a-fA-F]{1,4}:){1,7}[0-9a-fA-F]{1,4}";

    public boolean isValidIp(String ip) {
        if (ip == null || ip.isEmpty()) {
            return false;
        }

        // Check if the IP is a valid IPv4
        Pattern ipv4Pattern = Pattern.compile(IPV4_PATTERN);
        Matcher ipv4Matcher = ipv4Pattern.matcher(ip);
        if (ipv4Matcher.matches()) {
            return true;
        }

        // Check if the IP is a valid IPv6
        Pattern ipv6Pattern = Pattern.compile(IPV6_PATTERN);
        Matcher ipv6Matcher = ipv6Pattern.matcher(ip);
        return ipv6Matcher.matches();
    }
}
