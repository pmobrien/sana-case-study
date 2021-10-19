package com.pmobrien.webserver.neo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.Relationship;

import java.io.IOException;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class City extends NeoEntity {

  @Index(unique = true)
  private Integer idx;

  @Index(unique = true)
  private String name;

  @Index(unique = true)
  private String description;

  @Relationship(type = "FOR_CITY", direction = Relationship.INCOMING)
  private List<AirQualityThreshold> thresholds;

  public City() {}

  public City(City.Builder builder) {
    this.idx = builder.idx;
    this.name = builder.name;
    this.description = builder.description;
    this.thresholds = builder.thresholds;
  }

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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<AirQualityThreshold> getThresholds() {
    return thresholds;
  }

  public void setThresholds(List<AirQualityThreshold> thresholds) {
    this.thresholds = thresholds;
  }

  public static class Builder {

    private Integer idx;
    private String name;
    private String description;
    private List<AirQualityThreshold> thresholds;

    public Builder() {}

    public Builder idx(Integer idx) {
      this.idx = idx;
      return this;
    }

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder description(String description) {
      this.description = description;
      return this;
    }

    public Builder thresholds(List<AirQualityThreshold> thresholds) {
      this.thresholds = thresholds;
      return this;
    }

    public City build() {
      return new City(this);
    }
  }

  public static class Serializer extends StdSerializer<City> {

    public Serializer() {
      this(null);
    }

    public Serializer(Class<City> type) {
      super(type);
    }

    @Override
    public void serialize(City city, JsonGenerator generator, SerializerProvider provider) throws IOException {
      generator.writeStartObject();

      writeFields(city, generator);

      if(city.thresholds != null && !city.thresholds.isEmpty()) {
        generator.writeArrayFieldStart("thresholds");

        for (AirQualityThreshold threshold : city.thresholds) {
          generator.writeStartObject();
          new AirQualityThreshold.Serializer().writeFields(threshold, generator);
          generator.writeEndObject();
        }

        generator.writeEndArray();
      }

      generator.writeEndObject();
    }

    protected void writeFields(City city, JsonGenerator generator) throws IOException {
      generator.writeStringField("uuid", city.getUuid().toString());
      generator.writeNumberField("idx", city.getIdx());
      generator.writeStringField("name", city.getName());
      generator.writeStringField("description", city.getDescription());
    }
  }
}
