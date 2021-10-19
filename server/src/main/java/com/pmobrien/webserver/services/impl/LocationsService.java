package com.pmobrien.webserver.services.impl;

import com.pmobrien.webserver.neo.accessors.AirQualityAccessor;
import com.pmobrien.webserver.services.ILocationsService;

import javax.ws.rs.core.Response;

public class LocationsService implements ILocationsService {

  @Override
  public Response getAirQualityForLocation(String locationId) {
    return Response.ok(new AirQualityAccessor().getAirQualityForLocation(locationId)).build();
  }
}
