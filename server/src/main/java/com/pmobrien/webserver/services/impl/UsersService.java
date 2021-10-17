package com.pmobrien.webserver.services.impl;

import com.pmobrien.webserver.neo.accessors.UsersAccessor;
import com.pmobrien.webserver.services.IUsersService;
import com.pmobrien.webserver.services.model.CreateUserRequest;

import javax.ws.rs.core.Response;

public class UsersService implements IUsersService {

  @Override
  public Response createUser(CreateUserRequest createUserRequest) {
    return Response.status(javax.ws.rs.core.Response.Status.CREATED)
        .entity(new UsersAccessor().createNewUser(createUserRequest.getUsername()))
        .build();
  }
}
