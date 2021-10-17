package com.pmobrien.webserver.neo.accessors;

import com.pmobrien.webserver.aqicn.client.AirQualityClient;
import com.pmobrien.webserver.services.model.AirQuality;

public class AirQualityAccessor {

  public AirQuality getAirQualityForLocation(String city) {
    return AirQuality.fromAirQualityResponse(new AirQualityClient().getCityFeed(city));
  }
}
