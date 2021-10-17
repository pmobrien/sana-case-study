package com.pmobrien.webserver.services;

import com.pmobrien.webserver.services.model.CreateUserRequest;
import com.pmobrien.webserver.services.model.CreateUserThresholdRequest;

import javax.ws.rs.Consumes;
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

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/{username}/threshold")
  Response createUserThreshold(
      @PathParam("username") String username,
      CreateUserThresholdRequest createUserThresholdRequest
  );
}
