package com.pmobrien.webserver.aqicn.client;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class AirQualityClientTest {

  @ParameterizedTest
  @MethodSource("testBuildCityFeedUrlSource")
  public void testBuildCityFeedUrl(String city, String expected) {
    Assertions.assertEquals(expected, new AirQualityClient().buildCityFeedUrl(city));
  }

  @ParameterizedTest
  @MethodSource("testBuildCityFeedUrlExceptionsSource")
  public void testBuildCityFeedUrlExceptions(String city) {
    Assertions.assertThrows(IllegalArgumentException.class, () -> new AirQualityClient().buildCityFeedUrl(city));
  }

  private static Stream<Arguments> testBuildCityFeedUrlSource() {
    return Stream.of(
        Arguments.of("bangalore", "https://api.waqi.info/feed/bangalore/"),
        Arguments.of("beijing", "https://api.waqi.info/feed/beijing/"),
        Arguments.of("chicago", "https://api.waqi.info/feed/chicago/")
    );
  }

  private static Stream<Arguments> testBuildCityFeedUrlExceptionsSource() {
    return Stream.of(
        Arguments.of(""),
        Arguments.of((String) null)
    );
  }
}