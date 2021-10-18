package com.pmobrien.webserver.services;

import com.pmobrien.webserver.services.model.CreateTokenRequest;
import com.pmobrien.webserver.tokens.Token;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/tokens")
public interface ITokenService {

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  Token createToken(CreateTokenRequest createTokenRequest);
}
