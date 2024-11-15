package com.dwep1337.geolocation.util;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;

@Service
@Log4j2
public class DownloadDatabaseFile {

    public void downloadFile(String url, String outputPath) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS)  // follow redirects
                .connectTimeout(Duration.ofSeconds(10)).build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET().build();

        HttpResponse<InputStream> response = client.
                send(request, HttpResponse.BodyHandlers.ofInputStream());

        if (response.statusCode() == HttpStatus.OK.value()) {
            Path outputPathFile = Path.of(outputPath, "GeoLite2-City.mmdb");
            try (InputStream inputStream = response.body(); OutputStream outputStream = Files.newOutputStream(outputPathFile)) {
                inputStream.transferTo(outputStream);
            }
        } else {
            throw new RuntimeException("Failed: HTTP error code: " + response.statusCode());
        }
    }
}