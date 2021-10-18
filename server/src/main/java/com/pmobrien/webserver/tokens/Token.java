package com.pmobrien.webserver.tokens;

public class Token {

  private String token;

  public Token() {}

  public Token(Builder builder) {
    this.token = builder.token;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public static class Builder {

    private String token;

    public Builder() {}

    public Builder token(String token) {
      this.token = token;
      return this;
    }

    public Token build() {
      return new Token(this);
    }
  }
}
