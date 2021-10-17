package com.pmobrien.webserver.neo.model;

import org.neo4j.ogm.annotation.Relationship;

public class AirQualityThreshold extends NeoEntity {

  private String city;
  private Integer threshold;

  @Relationship(type = "HAS_THRESHOLD", direction = Relationship.INCOMING)
  private User user;

  public AirQualityThreshold() {}

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
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
