package com.dwep1337.geolocation;

import com.dwep1337.geolocation.util.DownloadDatabaseFile;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Log4j2
public class GeolocationApplication {

	public static void main(String[] args) {

		// first download GeoLite2 database file.
		if (!downloadDatabase()){
			log.error("Cannot download GeoLite2 database file.");
			return;
		}

		SpringApplication.run(GeolocationApplication.class, args);
	}

	// download link provided by https://github.com/P3TERX/GeoLite.mmdb
	private static  boolean downloadDatabase() {
		DownloadDatabaseFile download = new DownloadDatabaseFile();
		try {
			download.downloadFile("https://git.io/GeoLite2-City.mmdb", "src/main/resources/");
			return true;
		} catch (Exception e) {
			log.error("Error downloading GeoLite2 database file. {}", e.getMessage());
			return false;
		}
	}

}
