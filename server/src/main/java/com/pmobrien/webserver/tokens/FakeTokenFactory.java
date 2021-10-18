package com.pmobrien.webserver.tokens;

import java.util.UUID;

/**
 * Since this is a POC, and we don't really need an actual token, make a fake one. The format of the fake tokens is:
 * {UUID}:{username}, where {UUID} is a randomly generated UUID and {username} is any existing username. Fake token
 * creation will never fail.
 */
public class FakeTokenFactory implements ITokenFactory {

  private static final FakeTokenFactory INSTANCE = new FakeTokenFactory();

  private FakeTokenFactory() {}

  @Override
  public Token createTokenFor(String username) {
    return new Token.Builder()
        .token(String.join(":", UUID.randomUUID().toString(), username))
        .build();
  }

  public static FakeTokenFactory getInstance() {
    return INSTANCE;
  }
}
