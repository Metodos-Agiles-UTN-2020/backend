package com.backend.api.config.security;

import static com.backend.api.constants.SecurityConstants.EXPIRATION_TIME;
import static com.backend.api.constants.SecurityConstants.KEY;
import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  @SuppressWarnings("unused")
  private static final Logger logger = LogManager.getLogger(AuthenticationFilter.class);

  private static final Logger loggerAdministrativo = LogManager.getLogger("LoggerAdministrativo");

  private AuthenticationManager authenticationManager;

  public AuthenticationFilter(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
  }

  public static class AuthenticationUser {
    public String username;
    public String password;
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
      throws AuthenticationException {
    try {
      AuthenticationUser applicationUser =
          new ObjectMapper().readValue(req.getInputStream(), AuthenticationUser.class);

      return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
          applicationUser.username, applicationUser.password, new ArrayList<>()));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res,
      FilterChain chain, Authentication auth) throws IOException, ServletException {

    Date exp = new Date(System.currentTimeMillis() + EXPIRATION_TIME);
    Key key = Keys.hmacShaKeyFor(KEY.getBytes());
    Claims claims = Jwts.claims().setSubject(((User) auth.getPrincipal()).getUsername());
    String token = Jwts.builder().setClaims(claims).signWith(key, SignatureAlgorithm.HS512)
        .setExpiration(exp).compact();
    res.addHeader("token", token);

    loggerAdministrativo.info("[ingreso] usuario: " + ((User) auth.getPrincipal()).getUsername());
  }

  @Override
  protected void unsuccessfulAuthentication(HttpServletRequest request,
      HttpServletResponse response, AuthenticationException failed)
      throws IOException, ServletException {
    logger.info("Athentication request failed");
    super.unsuccessfulAuthentication(request, response, failed);
  }
}
