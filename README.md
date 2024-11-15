# GeoLocation API

This is a simple geolocation API that provides geolocation information for an IP address using the MaxMind GeoLite2 database.

## Features

- Validates IP address format (both IPv4 and IPv6).
- Fetches geolocation data such as country, city, latitude, longitude, and more.
- Returns information about the IP such as whether it’s a VPN or proxy.

## Requirements

- Java 21 or later
- MaxMind GeoLite2 Database (automatically downloaded)

## Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/dwep1337/geolocation.git
   cd geolocation
   ```

2. The `GeoLite2` database is automatically downloaded when the application starts. The database file `GeoLite2-City.mmdb` is saved in the `src/main/resources` directory.

3. Build and run the application:
   ```bash
   ./mvnw spring-boot:run
   ```

   The application will automatically download the GeoLite2 database file from the repository [GeoLite.mmdb by P3TERX](https://github.com/P3TERX/GeoLite.mmdb).

## Usage

To use the API, send a `GET` request to the following endpoint with the IP address as a query parameter:

```
http://localhost:8080/v1/api?ip=<IP_ADDRESS>
```

### Example Request:
```
GET http://localhost:8080/v1/api?ip=8.8.8.8
```

### Example Response:

```json
{
  "success": true,
  "message": "Success",
  "ipAddress": "8.8.8.8",
  "country": "United States",
  "countryIsoCode": "US",
  "city": null,
  "subdivision": null,
  "latitude": 37.751,
  "longitude": -97.822,
  "postalCode": null,
  "postalConfidence": null,
  "isVpn": false,
  "isProxy": false,
  "timezone": "America/Chicago",
  "continent": "North America",
  "metroCode": null
}
```
