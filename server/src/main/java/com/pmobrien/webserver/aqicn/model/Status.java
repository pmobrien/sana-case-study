package com.pmobrien.webserver.aqicn.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Status {
  OK("ok"),
  ERROR("error");

  private final String jsonValue;

  Status(String jsonValue) {
    this.jsonValue = jsonValue;
  }

  @JsonValue
  public String jsonValue() {
    return jsonValue;
  }
}
