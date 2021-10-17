package com.pmobrien.webserver.neo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.neo4j.ogm.annotation.Relationship;

import java.io.IOException;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AirQualityThreshold extends NeoEntity {

  private Integer threshold;

  @Relationship(type = "FOR_CITY", direction = Relationship.OUTGOING)
  private City city;

  @Relationship(type = "HAS_THRESHOLD", direction = Relationship.INCOMING)
  private User user;

  public AirQualityThreshold() {}

  public AirQualityThreshold(Builder builder) {
    this.threshold = builder.threshold;
    this.city = builder.city;
    this.user = builder.user;
  }

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

  public static class Builder {

    private Integer threshold;
    private City city;
    private User user;

    public Builder() {}

    public Builder threshold(Integer threshold) {
      this.threshold = threshold;
      return this;
    }

    public Builder city(City city) {
      this.city = city;
      return this;
    }

    public Builder user(User user) {
      this.user = user;
      return this;
    }

    public AirQualityThreshold build() {
      return new AirQualityThreshold(this);
    }
  }

  public static class Serializer extends StdSerializer<AirQualityThreshold> {

    public Serializer() {
      this(null);
    }

    public Serializer(Class<AirQualityThreshold> type) {
      super(type);
    }

    @Override
    public void serialize(AirQualityThreshold threshold, JsonGenerator generator, SerializerProvider provider) throws IOException {
      generator.writeStartObject();

      writeFields(threshold, generator);

      if(threshold.getCity() != null) {
        generator.writeFieldName("city");
        generator.writeStartObject();
        new City.Serializer().writeFields(threshold.getCity(), generator);
        generator.writeEndObject();
      }

      if(threshold.getUser() != null) {
        generator.writeFieldName("user");
        generator.writeStartObject();
        new User.Serializer().writeFields(threshold.getUser(), generator);
        generator.writeEndObject();
      }

      generator.writeEndObject();
    }

    protected void writeFields(AirQualityThreshold threshold, JsonGenerator generator) throws IOException {
      generator.writeStringField("uuid", threshold.getUuid().toString());
      generator.writeNumberField("threshold", threshold.getThreshold());
    }
  }
}
