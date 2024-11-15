package com.dwep1337.geolocation.util;


import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ValidIP {

    // Regex ipv4
    private static final String IPV4_PATTERN =
            "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";

    // Regex ipv6
    private static final String IPV6_PATTERN =
            "([0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}";


    public boolean isValidIp(String ip) {
        Pattern ipv4Pattern = Pattern.compile(IPV4_PATTERN);
        Pattern ipv6Pattern = Pattern.compile(IPV6_PATTERN);
        Matcher ipv4Matcher = ipv4Pattern.matcher(ip);
        Matcher ipv6Matcher = ipv6Pattern.matcher(ip);
        return ipv4Matcher.matches() || ipv6Matcher.matches();
    }
}
