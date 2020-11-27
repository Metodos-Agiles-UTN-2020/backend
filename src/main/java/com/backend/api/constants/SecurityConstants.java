package com.backend.api.constants;

public class SecurityConstants {
  public static final String SIGN_UP_URL = "/users/record";
  // TODO parametrizar KEY con variables de entorno seteados con docker-compose
  public static final String KEY =
      "5u8x!A%D*G-KaPdSgVkYp3s6v9y$B?E(H+MbQeThWmZq4t7w!z%C*F)J@NcRfUjX";
  public static final String AUTH_HEADER_NAME = "Authorization";
  public static final Long EXPIRATION_TIME = 1000L * 60 * 30;
}
