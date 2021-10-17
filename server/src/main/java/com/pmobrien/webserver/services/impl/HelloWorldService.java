package com.pmobrien.webserver.services.impl;

import com.pmobrien.webserver.neo.accessors.HelloWorldAccessor;
import com.pmobrien.webserver.services.IHelloWorldService;
import javax.ws.rs.core.Response;

public class HelloWorldService implements IHelloWorldService {

  @Override
  public Response helloWorld() {
    return Response.ok(new HelloWorldAccessor().getHelloWorld()).build();
  }
}
