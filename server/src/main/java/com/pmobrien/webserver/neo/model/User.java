package com.pmobrien.webserver.neo.model;

import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

public class User extends NeoEntity {

  private String username;

  @Relationship(type = "HAS_THRESHOLD", direction = Relationship.OUTGOING)
  private List<AirQualityThreshold> thresholds;

  public User() {}

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public List<AirQualityThreshold> getThresholds() {
    return thresholds;
  }

  public void setThresholds(List<AirQualityThreshold> thresholds) {
    this.thresholds = thresholds;
  }
}
