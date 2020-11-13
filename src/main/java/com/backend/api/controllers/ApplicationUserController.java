package com.backend.api.controllers;


import java.security.Principal;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.backend.api.models.ApplicationUser;
import com.backend.api.repositories.TipoLicenciaRepository;
import com.backend.api.services.ApplicationUserDetailsService;


@Component
@RestController
public class ApplicationUserController {

  @SuppressWarnings("unused")
  private static final Logger logger = LogManager.getLogger(ApplicationUserController.class);

  @Autowired
  private ApplicationUserDetailsService applicationUserDetailsService;

  @Autowired
  private TipoLicenciaRepository tipoLicenciaRepository;


  @GetMapping("/user")
  ApplicationUser getUserDetails(Principal principal) {

    String username = principal.getName();
    ApplicationUser user = applicationUserDetailsService.getUserDetails(username);

    return user;
  }
}
