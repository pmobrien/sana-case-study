package com.pmobrien.webserver.neo.accessors;

import com.pmobrien.webserver.neo.Sessions;
import com.pmobrien.webserver.neo.pojo.HelloWorld;

public class HelloWorldAccessor {

  public HelloWorldAccessor() {}
  
  public HelloWorld getHelloWorld() {
    return Sessions.returningSessionOperation(
        session -> session.loadAll(HelloWorld.class).toArray(new HelloWorld[] {})[0]
    );
  }
}
