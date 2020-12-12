package com.backend.api.controllers;


import static com.backend.api.constants.SecurityConstants.AUTH_HEADER_NAME;
import static com.backend.api.constants.SecurityConstants.KEY;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.backend.api.constants.TipoUsuario;
import com.backend.api.models.ApplicationUser;
import com.backend.api.services.ApplicationUserDetailsService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;


@Component
@RestController
public class ApplicationUserController {

  @SuppressWarnings("unused")
  private static final Logger logger = LogManager.getLogger(ApplicationUserController.class);

  @SuppressWarnings("unused")
  private static final Logger loggerAdministrativo = LogManager.getLogger("LoggerAdministrativo");

  @Autowired
  private ApplicationUserDetailsService applicationUserDetailsService;

  /*
   * @GetMapping("/user") ApplicationUser getUserDetails(Principal principal) {
   * 
   * String username = principal.getName(); ApplicationUser user =
   * applicationUserDetailsService.getUserDetails(username);
   * 
   * return user; }
   */

  @GetMapping("/user")
  ApplicationUser getUserByDni(
      @RequestParam(name = "nroDocumento", required = true) String nroDocumento) {
    ApplicationUser user = applicationUserDetailsService.getUserByDNI(nroDocumento);
    return user;
  }

  @PutMapping("/user")
  ApplicationUser updateUser(@RequestBody ApplicationUser updateUser, HttpServletRequest request)
      throws Exception {
    String token = request.getHeader(AUTH_HEADER_NAME);

    @SuppressWarnings("deprecation")
    String username = Jwts.parser().setSigningKey(Keys.hmacShaKeyFor(KEY.getBytes()))
        .parseClaimsJws(token).getBody().get("sub").toString();

    ApplicationUser user = applicationUserDetailsService.getUserDetails(username);

    if (user.getTipoUsuario() != TipoUsuario.SUPERUSUARIO) {
      throw new Exception();
      // TODO throw forbidden
    }

    applicationUserDetailsService.updateUser(updateUser);

    return updateUser;
  }

}
