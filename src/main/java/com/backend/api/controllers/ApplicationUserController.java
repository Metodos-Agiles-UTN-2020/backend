package com.backend.api.controllers;


import static com.backend.api.constants.SecurityConstants.AUTH_HEADER_NAME;
import static com.backend.api.constants.SecurityConstants.KEY;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import com.backend.api.config.security.AuthorizationFilter;
import com.backend.api.models.ApplicationUser;
import com.backend.api.services.ApplicationUserDetailsService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;


@Component
@RestController
public class ApplicationUserController {

  @SuppressWarnings("unused")
  private static final Logger logger = LogManager.getLogger(AuthorizationFilter.class);

  @Autowired
  private ApplicationUserDetailsService applicationUserDetailsService;

  @GetMapping("/user")
  ApplicationUser getUserDetails(@RequestHeader(AUTH_HEADER_NAME) String token) {

    @SuppressWarnings("deprecation")
    String username = (String) Jwts.parser().setSigningKey(Keys.hmacShaKeyFor(KEY.getBytes()))
        .parseClaimsJws(token).getBody().get("sub");

    ApplicationUser user = applicationUserDetailsService.getUserDetails(username);

    return user;
  }

}
