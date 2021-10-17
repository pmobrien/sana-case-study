package com.pmobrien.webserver.services;

import com.pmobrien.webserver.services.model.AirQuality;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/locations")
public interface ILocationsService {

  @GET
  @Path("/{locationId}/air-quality")
  @Produces(MediaType.APPLICATION_JSON)
  AirQuality getAirQualityForLocation(@PathParam("locationId") String locationId);
}
