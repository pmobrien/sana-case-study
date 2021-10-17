package com.pmobrien.webserver.neo.accessors;

import com.pmobrien.webserver.neo.Sessions;
import com.pmobrien.webserver.neo.model.AirQualityThreshold;
import com.pmobrien.webserver.neo.model.City;
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

  public AirQualityThreshold createThreshold(String username, String cityName, Integer value) {
    User user = getUserByUsername(username);
    City city = new CitiesAccessor().getCity(cityName);

    return Sessions.returningSessionOperation(session -> {
      HashMap<String, Object> parameters = new HashMap<String, Object>() {{
        put("cityName", city.getName());
        put("username", user.getUsername());
        put("thresholdValue", value);
      }};

      AirQualityThreshold threshold = session.queryForObject(AirQualityThreshold.class, Queries.GET_USER_THRESHOLD_FOR_CITY, parameters);
      if (threshold == null) {
        threshold = new AirQualityThreshold.Builder()
            .threshold(value)
            .city(city)
            .user(user)
            .build();
      } else {
        threshold.setThreshold(value);
      }

      session.save(threshold);

      return threshold;
    });
  }

  private static class Queries {

    private static final String GET_USER_BY_USERNAME = new StringBuilder()
        .append("MATCH (user:User { username: {username} })").append(System.lineSeparator())
        .append("RETURN user")
        .toString();

    private static final String GET_USER_THRESHOLD_FOR_CITY = new StringBuilder()
        .append("MATCH (city:City { name: {cityName} })").append(System.lineSeparator())
        .append("MATCH (user:User { username: {username} })").append(System.lineSeparator())
        .append("MATCH (user)-[has_threshold:HAS_THRESHOLD]->(threshold:AirQualityThreshold)-[for_city:FOR_CITY]->(city)").append(System.lineSeparator())
        .append("RETURN threshold, for_city, city, user, has_threshold")
        .toString();
  }
}
