package com.pmobrien.webserver.services;

import com.pmobrien.webserver.services.model.CreateUserRequest;
import com.pmobrien.webserver.services.model.CreateUserThresholdRequest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users")
public interface IUsersService {

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  Response createUser(CreateUserRequest createUserRequest);

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/{username}/thresholds")
  Response getAirQualityThresholdsForUser(
      @PathParam("username") String username
  );

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/{username}/thresholds")
  Response createUserThreshold(
      @PathParam("username") String username,
      CreateUserThresholdRequest createUserThresholdRequest
  );

  @DELETE
  @Path("/{username}/thresholds/{thresholdId}")
  Response deleteUserThreshold(
      @PathParam("username") String username,
      @PathParam("thresholdId") String thresholdId
  );
}
