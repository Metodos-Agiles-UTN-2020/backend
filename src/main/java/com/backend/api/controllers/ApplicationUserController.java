package com.backend.api.controllers;


import java.security.Principal;
import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.backend.api.config.SecurityConfiguration;
import com.backend.api.constants.TipoUsuario;
import com.backend.api.exceptions.ForbiddenException;
import com.backend.api.models.ApplicationUser;
import com.backend.api.services.ApplicationUserDetailsService;


@Component
@RestController
public class ApplicationUserController {

  @SuppressWarnings("unused")
  private static final Logger logger = LogManager.getLogger(ApplicationUserController.class);

  @SuppressWarnings("unused")
  private static final Logger loggerAdministrativo = LogManager.getLogger("LoggerAdministrativo");

  @Autowired
  private ApplicationUserDetailsService applicationUserDetailsService;

  @Autowired
  private SecurityConfiguration securityConfiguration;

  @GetMapping("/user")
  ApplicationUser getUserDetails(Principal principal) {

    String username = principal.getName();
    ApplicationUser user = applicationUserDetailsService.getUserDetails(username);

    return user;
  }

  @GetMapping("/user/{nroDocumento}")
  ApplicationUser getUserByDni(@PathVariable(value = "nroDocumento") String nroDocumento) {

    ApplicationUser user = applicationUserDetailsService.getUserByDNI(nroDocumento);
    return user;
  }

  @PostMapping("/updateuser")
  ApplicationUser updateUser(@RequestBody ApplicationUser updateUser, Principal principal)
      throws Exception {

    String username = principal.getName();

    ApplicationUser user = applicationUserDetailsService.getUserDetails(username);

    if (user.getTipoUsuario() != TipoUsuario.SUPERUSUARIO) {
      throw new ForbiddenException();
    }

    applicationUserDetailsService.updateUser(updateUser);

    return updateUser;
  }

  @PostMapping("/user")
  ApplicationUser saveUser(@Valid @RequestBody ApplicationUser newUser, Principal principal)
      throws Exception {

    String username = principal.getName();

    ApplicationUser user = applicationUserDetailsService.getUserDetails(username);

    if (user.getTipoUsuario() != TipoUsuario.SUPERUSUARIO) {
      throw new ForbiddenException();
    }

    if (newUser.getTipoUsuario() == TipoUsuario.SUPERUSUARIO) {
      throw new ForbiddenException();
    }

    String encodedPassword =
        securityConfiguration.passwordEncoder().encode(newUser.getPassword().toString());

    newUser.setPassword(encodedPassword);

    newUser = applicationUserDetailsService.saveUser(newUser);

    return newUser;

  }
}
