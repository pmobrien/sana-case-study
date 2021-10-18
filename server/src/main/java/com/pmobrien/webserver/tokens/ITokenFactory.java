package com.pmobrien.webserver.tokens;

public interface ITokenFactory {

  Token createTokenFor(String username);

  static ITokenFactory getConfiguredTokenFactory() {
    return FakeTokenFactory.getInstance();  // always return the fake token factory for now
  }
}
