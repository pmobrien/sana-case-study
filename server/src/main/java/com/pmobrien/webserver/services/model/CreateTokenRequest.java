package com.pmobrien.webserver.services.model;

public class CreateTokenRequest {

  private String username;

  public CreateTokenRequest() {}

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }
}
