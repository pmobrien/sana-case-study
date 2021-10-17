package com.pmobrien.webserver.neo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class User extends NeoEntity {

  private String username;

  @Relationship(type = "HAS_THRESHOLD", direction = Relationship.OUTGOING)
  private List<AirQualityThreshold> thresholds;

  public User() {}

  public User(User.Builder builder) {
    this.username = builder.username;
    this.thresholds = builder.thresholds;
  }

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

  public static class Builder {

    private String username;
    private List<AirQualityThreshold> thresholds;

    public Builder() {}

    public Builder username(String username) {
      this.username = username;
      return this;
    }

    public Builder thresholds(List<AirQualityThreshold> thresholds) {
      this.thresholds = thresholds;
      return this;
    }

    public User build() {
      return new User(this);
    }
  }
}
