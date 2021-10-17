package com.pmobrien.webserver.aqicn.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AirQualityResponse {

  private Status status;
  private Data data;

  public AirQualityResponse() {}

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public Data getData() {
    return data;
  }

  public void setData(Data data) {
    this.data = data;
  }

  @JsonIgnore
  public boolean isSuccess() {
    return Status.OK.equals(status);
  }

  @JsonIgnore
  public boolean isError() {
    return Status.ERROR.equals(status);
  }
}
