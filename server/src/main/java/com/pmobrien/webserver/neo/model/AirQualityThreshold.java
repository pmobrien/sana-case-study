package com.pmobrien.webserver.neo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.neo4j.ogm.annotation.Relationship;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AirQualityThreshold extends NeoEntity {

  private Integer threshold;

  @Relationship(type = "FOR_CITY", direction = Relationship.OUTGOING)
  private City city;

  @Relationship(type = "HAS_THRESHOLD", direction = Relationship.INCOMING)
  private User user;

  public AirQualityThreshold() {}

  public City getCity() {
    return city;
  }

  public void setCity(City city) {
    this.city = city;
  }

  public Integer getThreshold() {
    return threshold;
  }

  public void setThreshold(Integer threshold) {
    this.threshold = threshold;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
