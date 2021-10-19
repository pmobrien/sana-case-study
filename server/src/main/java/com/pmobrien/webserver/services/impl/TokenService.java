package com.pmobrien.webserver.services.impl;

import com.pmobrien.webserver.neo.accessors.UsersAccessor;
import com.pmobrien.webserver.services.ITokenService;
import com.pmobrien.webserver.services.model.CreateTokenRequest;
import com.pmobrien.webserver.tokens.ITokenFactory;

import javax.ws.rs.core.Response;

public class TokenService implements ITokenService {

  @Override
  public Response createToken(CreateTokenRequest createTokenRequest) {
    // if the user doesn't exist yet, create it
    // this is our "registration" logic
    if (new UsersAccessor().getUserByUsername(createTokenRequest.getUsername()) == null) {
      new UsersAccessor().createNewUser(createTokenRequest.getUsername());
    }

    return Response.status(Response.Status.CREATED)
        .entity(ITokenFactory.getConfiguredTokenFactory().createTokenFor(createTokenRequest.getUsername()))
        .build();
  }
}
