package com.backend.api.config.security;

import static com.backend.api.constants.SecurityConstants.AUTH_HEADER_NAME;
import static com.backend.api.constants.SecurityConstants.KEY;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class AuthorizationFilter extends BasicAuthenticationFilter {

  @SuppressWarnings("unused")
  private static final Logger logger = LogManager.getLogger(AuthorizationFilter.class);

  public AuthorizationFilter(AuthenticationManager authManager) {
    super(authManager);
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain) throws IOException, ServletException {
    String header = request.getHeader(AUTH_HEADER_NAME);

    if (header == null) {
      chain.doFilter(request, response);
      return;
    }

    UsernamePasswordAuthenticationToken authentication = authenticate(request);

    SecurityContextHolder.getContext().setAuthentication(authentication);
    chain.doFilter(request, response);
  }

  @SuppressWarnings("deprecation")
  private UsernamePasswordAuthenticationToken authenticate(HttpServletRequest request) {
    String token = request.getHeader(AUTH_HEADER_NAME);
    if (token != null) {
      String user = Jwts.parser().setSigningKey(Keys.hmacShaKeyFor(KEY.getBytes()))
          .parseClaimsJws(token).getBody().get("sub").toString();

      if (user != null) {
        return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
      } else {
        return null;
      }

    }
    return null;
  }
}
