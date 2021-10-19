package com.pmobrien.webserver.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/locations")
public interface ILocationsService {

  @GET
  @Path("/{locationId}/air-quality")
  @Produces(MediaType.APPLICATION_JSON)
  Response getAirQualityForLocation(@PathParam("locationId") String locationId);
}
