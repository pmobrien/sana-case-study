package com.pmobrien.webserver.neo.accessors;

import com.pmobrien.webserver.neo.Sessions;
import com.pmobrien.webserver.neo.model.User;

import java.util.HashMap;

public class UsersAccessor {

  public User createNewUser(String username) {
    Sessions.sessionOperation(session ->
        session.save(
            new User.Builder()
                .username(username)
                .build()
        )
    );

    return getUserByUsername(username);
  }

  public User getUserByUsername(String username) {
    return Sessions.returningSessionOperation(session ->
      session.queryForObject(
          User.class,
          Queries.GET_USER_BY_USERNAME,
          new HashMap<String, String>() {{
            put("username", username);
          }}
      )
    );
  }

  private static class Queries {

    private static final String GET_USER_BY_USERNAME = new StringBuilder()
        .append("MATCH (user:User { username: {username} })").append(System.lineSeparator())
        .append("RETURN user")
        .toString();
  }
}
