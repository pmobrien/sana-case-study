package com.pmobrien.webserver.services.model;

public class CreateUserThresholdRequest {

  private String locationId;
  private Integer threshold;

  public CreateUserThresholdRequest() {}

  public String getLocationId() {
    return locationId;
  }

  public void setLocationId(String locationId) {
    this.locationId = locationId;
  }

  public Integer getThreshold() {
    return threshold;
  }

  public void setThreshold(Integer threshold) {
    this.threshold = threshold;
  }
}
