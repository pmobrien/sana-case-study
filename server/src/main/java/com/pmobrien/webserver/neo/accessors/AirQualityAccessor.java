package com.pmobrien.webserver.neo.accessors;

import com.google.common.collect.Lists;
import com.pmobrien.webserver.aqicn.client.AirQualityClient;
import com.pmobrien.webserver.neo.Sessions;
import com.pmobrien.webserver.neo.model.AirQualityThreshold;
import com.pmobrien.webserver.services.model.AirQuality;

import java.util.HashMap;
import java.util.List;

public class AirQualityAccessor {

  public AirQuality getAirQualityForLocation(String city) {
    return AirQuality.fromAirQualityResponse(new AirQualityClient().getCityFeed(city));
  }

  public List<AirQualityThreshold> getAirQualityThresholdsForUser(String username) {
    return Sessions.returningSessionOperation(session ->
        Lists.newArrayList(
            session.query(
                AirQualityThreshold.class,
                Queries.GET_AIR_QUALITIES_FOR_USER,
                new HashMap<String, String>() {{
                  put("username", username);
                }}
            )
        )
    );
  }

  private static class Queries {

    private static final String GET_AIR_QUALITIES_FOR_USER = new StringBuilder()
        .append("MATCH (user:User { username: {username} })").append(System.lineSeparator())
        .append("MATCH (user)-[has_threshold:HAS_THRESHOLD]->(threshold:AirQualityThreshold)").append(System.lineSeparator())
        .append("MATCH (threshold)-[for_city:FOR_CITY]->(city:City)").append(System.lineSeparator())
        .append("RETURN threshold, has_threshold, user, for_city, city")
        .toString();
  }
}
