package com.pmobrien.webserver.mappers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.pmobrien.webserver.neo.model.AirQualityThreshold;
import com.pmobrien.webserver.neo.model.City;
import com.pmobrien.webserver.neo.model.HelloWorld;
import com.pmobrien.webserver.neo.model.User;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

@Provider
@Produces(MediaType.APPLICATION_JSON)
public class DefaultObjectMapper implements ContextResolver<ObjectMapper> {
  
  @Override
  public ObjectMapper getContext(Class<?> type) {
    return new ObjectMapper()
        .configure(SerializationFeature.INDENT_OUTPUT, true)
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        .registerModule(
            new SimpleModule()
                .addSerializer(AirQualityThreshold.class, new AirQualityThreshold.Serializer())
                .addSerializer(City.class, new City.Serializer())
                .addSerializer(HelloWorld.class, new HelloWorld.Serializer())
                .addSerializer(User.class, new User.Serializer())
        );
  }
}
