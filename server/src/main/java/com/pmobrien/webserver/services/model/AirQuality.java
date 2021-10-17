package com.pmobrien.webserver.services.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pmobrien.webserver.aqicn.model.AirQualityResponse;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AirQuality {

  private Integer idx;
  private String city;
  private Number aqi;

  public AirQuality() {}

  public AirQuality(AirQuality.Builder builder) {
    this.idx = builder.idx;
    this.city = builder.city;
    this.aqi = builder.aqi;
  }

  public Integer getIdx() {
    return idx;
  }

  public void setIdx(Integer idx) {
    this.idx = idx;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public Number getAqi() {
    return aqi;
  }

  public void setAqi(Number aqi) {
    this.aqi = aqi;
  }

  public static AirQuality fromAirQualityResponse(AirQualityResponse response) {
    return new Builder()
        .idx(response.getData().getIdx())
        .city(response.getData().getCity().getName())
        .aqi(response.getData().getAqi())
        .build();
  }

  public static class Builder {

    private Integer idx;
    private String city;
    private Number aqi;

    public Builder() {}

    public Builder idx(Integer idx) {
      this.idx = idx;
      return this;
    }

    public Builder city(String city) {
      this.city = city;
      return this;
    }

    public Builder aqi(Number aqi) {
      this.aqi = aqi;
      return this;
    }

    public AirQuality build() {
      return new AirQuality(this);
    }
  }
}
