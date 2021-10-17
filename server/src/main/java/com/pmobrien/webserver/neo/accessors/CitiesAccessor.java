package com.pmobrien.webserver.neo.accessors;

import com.pmobrien.webserver.aqicn.client.AirQualityClient;
import com.pmobrien.webserver.aqicn.model.AirQualityResponse;
import com.pmobrien.webserver.neo.Sessions;
import com.pmobrien.webserver.neo.model.City;

import javax.ws.rs.NotFoundException;
import java.util.HashMap;

public class CitiesAccessor {

  public City getCity(String cityName) {
    // kind of a hack, but the city search api response doesn't have the city name key in it
    AirQualityResponse aq = new AirQualityClient().getCityFeed(cityName);
    if (aq.isError()) {
      throw new NotFoundException(String.format("City (%s) not found.", cityName));
    }

    return Sessions.returningSessionOperation(session -> {
      City city = session.queryForObject(
          City.class,
          Queries.GET_CITY_BY_NAME,
          new HashMap<String, String>() {{
            put("cityName", cityName);
          }}
      );

      // if we don't have the city in the graph yet, save it
      if (city == null) {
        city = new City.Builder()
            .idx(aq.getData().getIdx())
            .name(cityName)
            .description(aq.getData().getCity().getName())
            .build();

        session.save(city);
      }

      return city;
    });
  }

  private static class Queries {

    private static final String GET_CITY_BY_NAME = new StringBuilder()
        .append("MATCH (city:City { name: {cityName} })").append(System.lineSeparator())
        .append("RETURN city")
        .toString();
  }
}
