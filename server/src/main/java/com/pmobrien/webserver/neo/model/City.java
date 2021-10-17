package com.pmobrien.webserver.neo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class City {

  private Integer idx;
  private String name;

  @Relationship(type = "FOR_CITY", direction = Relationship.INCOMING)
  private List<AirQualityThreshold> thresholds;

  public City() {}

  public Integer getIdx() {
    return idx;
  }

  public void setIdx(Integer idx) {
    this.idx = idx;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<AirQualityThreshold> getThresholds() {
    return thresholds;
  }

  public void setThresholds(List<AirQualityThreshold> thresholds) {
    this.thresholds = thresholds;
  }
}
