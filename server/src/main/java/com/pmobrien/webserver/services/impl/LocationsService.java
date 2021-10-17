package com.pmobrien.webserver.services.impl;

import com.pmobrien.webserver.neo.accessors.AirQualityAccessor;
import com.pmobrien.webserver.services.model.AirQuality;
import com.pmobrien.webserver.services.ILocationsService;

public class LocationsService implements ILocationsService {

  @Override
  public AirQuality getAirQualityForLocation(String locationId) {
    return new AirQualityAccessor().getAirQualityForLocation(locationId);
  }
}
