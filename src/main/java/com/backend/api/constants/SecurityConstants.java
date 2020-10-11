package com.backend.api.constants;

public class SecurityConstants {
  public static final String SIGN_UP_URL = "/users/record";
  // TODO parametrizar KEY con variables de entorno seteados con docker-compose
  public static final String KEY =
      "?D(G+KbPeSgVkYp3s6v9y$B&E)H@McQfTjWmZq4t7w!z%C*F-JaNdRgUkXp2r5u8";
  public static final String AUTH_HEADER_NAME = "Authorization";
  public static final Long EXPIRATION_TIME = 1000L * 60 * 30;
}
