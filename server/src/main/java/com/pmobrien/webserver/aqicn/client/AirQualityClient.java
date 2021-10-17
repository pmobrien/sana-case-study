package com.pmobrien.webserver.aqicn.client;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Strings;
import com.pmobrien.webserver.Application;
import com.pmobrien.webserver.aqicn.model.AirQualityResponse;
import org.glassfish.jersey.filter.LoggingFilter;

import javax.ws.rs.client.ClientBuilder;

public class AirQualityClient {

  private static final String BASE_URL = "https://api.waqi.info";
  private static final String FEED_URL = String.join("/", BASE_URL, "feed");

  public AirQualityClient() {}

  public AirQualityResponse getCityFeed(String city) {
    return ClientBuilder.newClient()
        .register(new LoggingFilter())
        .target(buildCityFeedUrl(city))
        .queryParam("token", Application.getProperties().getConfiguration().getAqicn().getToken())
        .request()
        .get(AirQualityResponse.class);
  }

  @VisibleForTesting
  String buildCityFeedUrl(String city) {
    if (Strings.isNullOrEmpty(city)) {
      throw new IllegalArgumentException("City cannot be null.");
    }

    return String.join("/", FEED_URL, city, "");
  }
}
