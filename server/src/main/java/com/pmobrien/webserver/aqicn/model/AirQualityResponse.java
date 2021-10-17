package com.pmobrien.webserver.aqicn.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AirQualityResponse {

  public enum Status {
    OK("ok"),
    ERROR("error"),
    UNKNOWN("");

    private final String jsonValue;

    Status(String jsonValue) {
      this.jsonValue = jsonValue;
    }

    @JsonValue
    public String jsonValue() {
      return jsonValue;
    }

    public static Status fromResponse(String response) {
      for (Status status : Status.values()) {
        if (Status.valueOf(response.toUpperCase()).equals(status)) {
          return status;
        }
      }

      System.out.printf("Unknown status response: %s.", response);

      return UNKNOWN;
    }
  }

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

  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Data {

    private Integer aqi;
    private Integer idx;
    private List<Attributions> attributions;
    private City city;

    public Data() {}

    public Integer getAqi() {
      return aqi;
    }

    public void setAqi(Integer aqi) {
      this.aqi = aqi;
    }

    public Integer getIdx() {
      return idx;
    }

    public void setIdx(Integer idx) {
      this.idx = idx;
    }

    public List<Attributions> getAttributions() {
      return attributions;
    }

    public void setAttributions(List<Attributions> attributions) {
      this.attributions = attributions;
    }

    public City getCity() {
      return city;
    }

    public void setCity(City city) {
      this.city = city;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Attributions {

      private String url;
      private String name;

      public Attributions() {}

      public String getUrl() {
        return url;
      }

      public void setUrl(String url) {
        this.url = url;
      }

      public String getName() {
        return name;
      }

      public void setName(String name) {
        this.name = name;
      }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class City {

      private List<Integer> geo;
      private String name;
      private String url;

      public City() {}

      public List<Integer> getGeo() {
        return geo;
      }

      public void setGeo(List<Integer> geo) {
        this.geo = geo;
      }

      public String getName() {
        return name;
      }

      public void setName(String name) {
        this.name = name;
      }

      public String getUrl() {
        return url;
      }

      public void setUrl(String url) {
        this.url = url;
      }
    }
  }
}
