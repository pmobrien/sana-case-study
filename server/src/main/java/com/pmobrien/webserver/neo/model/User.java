package com.pmobrien.webserver.neo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.neo4j.ogm.annotation.Relationship;

import java.io.IOException;
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

  public static class Serializer extends StdSerializer<User> {

    public Serializer() {
      this(null);
    }

    public Serializer(Class<User> type) {
      super(type);
    }

    @Override
    public void serialize(User user, JsonGenerator generator, SerializerProvider provider) throws IOException {
      generator.writeStartObject();

      writeFields(user, generator);

      if(user.thresholds != null && !user.thresholds.isEmpty()) {
        generator.writeArrayFieldStart("thresholds");

        for (AirQualityThreshold threshold : user.thresholds) {
          generator.writeStartObject();
          new AirQualityThreshold.Serializer().writeFields(threshold, generator);
          generator.writeEndObject();
        }

        generator.writeEndArray();
      }

      generator.writeEndObject();
    }

    protected void writeFields(User user, JsonGenerator generator) throws IOException {
      generator.writeStringField("uuid", user.getUuid().toString());
      generator.writeStringField("username", user.getUsername());
    }
  }
}
